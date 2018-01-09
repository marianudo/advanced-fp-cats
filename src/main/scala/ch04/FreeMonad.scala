package ch04

import scala.collection.mutable

object FreeMonad {
  object KeyValueDsl {
    // GADT with the DSL syntax
    sealed trait KVStoreA[A]
    case class Put[T](key: String, value: T) extends KVStoreA[Unit]
    case class Get[T](key: String) extends KVStoreA[Option[T]]
    case class Delete(key: String) extends KVStoreA[Unit]

    // Free type
    import cats.free.Free
    type KVStore[A] = Free[KVStoreA, A]

    // Key value primitive operations in terms of Free
    def put[T](key: String, value: T): KVStore[Unit] =
      Free.liftF[KVStoreA, Unit](Put(key, value))

    def get[T](key: String): KVStore[Option[T]] =
      Free.liftF[KVStoreA, Option[T]](Get(key))

    def delete(key: String): KVStore[Unit] =
      Free.liftF[KVStoreA, Unit](Delete(key))

    // Derived operations
    def update[T](key: String, newValue: T): KVStore[Option[T]] =
      for {
        currentValue <- get[T](key)
        _ <- put(key, newValue)
      } yield currentValue

    def updateWithFx[T](key: String, mapFx: T => T): KVStore[Unit] = {
      import cats.syntax.applicative._

      for {
        currentValue <- get[T](key)
        _ <- currentValue.map(v => put(key, mapFx(v))).getOrElse(().pure[KVStore])
      } yield ()
    }

    // Create a random program using my DSL
    def program() =
      for {
        _ <- put("Player1", 7)
        _ <- put("Player2", 3)
        _ <- updateWithFx[Int]("Player1", _ + 3)
        result1 <- get[Int]("Player1").map(_.getOrElse(0))
        result2 <- get[Int]("Player2").map(_.getOrElse(0))
      } yield result1 + result2

    // Naive, impure interpreter based on Id
    import cats.{Id, ~>}

    object ImpureCompiler extends (KVStoreA ~> Id) {

      private var map: mutable.Map[String, Any] = mutable.Map.empty

      override def apply[A](fa: KVStoreA[A]): A = {
        fa match {
          case Put(k, v) =>
            map.put(k, v)
            println(s"Put($k, $v) => $map")
            ()

          case Get(k) =>
            val result = map.get(k)
            println(s"Get($k) => $result => $map")
            result

          case Delete(k) =>
            val result = map.remove(k)
            println(s"Remove($k) => $result => $map")
            ()
        }
      }
    }

    // Pure, functional interpreter based on the State Monad
    import cats.data.State
    type KVState[A] = State[Map[String, Any], A]

    object PureCompiler extends (KVStoreA ~> KVState) {
      override def apply[A](fa: KVStoreA[A]) = {
        fa match {
          case Put(k, v) => State.modify(_.updated(k, v))

          case Get(k) => State.inspect(_.get(k))

          case Delete(k) => State.modify(_ - k)
        }
      }
    }
  }
}
