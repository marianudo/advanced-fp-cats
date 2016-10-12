import cats.Applicative

import scala.concurrent.Await
import scala.language.higherKinds
import scala.util.Random

trait MonolithicCalc {
    def generateVal(): Int
    def doubleVal(n: Int): Int
}

object MonolithicCalcImpl extends MonolithicCalc {
    override def generateVal(): Int = Random.nextInt(100)

    override def doubleVal(n: Int): Int = n * 2
}

object MonolithicProgram {
    def run() = {
        import MonolithicCalcImpl._

        val n = generateVal()
        doubleVal(n)
    }
}

MonolithicProgram.run()

//trait Applicative[F[_]] {
//    def pure[A](a: A): F[A]
//}
//
//trait Monad[F[_]] extends Applicative[F] {
//    final def flatMap[A, B](a: A)(fx: A => F[B]): F[B] = fx(a)
//
//    def map[A, B](a: A)(fx: A => B): F[B] =
//        flatMap(a)(a => pure(fx(a)))
//}
//
//object Monad {
//    object FutureMonad extends Monad[Future] {
//        override def pure[A](a: A): Future[A] = Future successful a
//    }
//
//    object OptionMonad extends Monad[Option] {
//        override def pure[A](a: A): Option[A] = Some(a)
//    }
//
//    implicit class MonadSyntax[F[_], T](n: T) {
//        def flatMap[B](fx: T => F[B])(implicit monad: Monad[F]): F[B] =
//            monad.flatMap(n)(fx)
//
//        def pure(t: T)(implicit monad: Monad[F]): F[T] = monad.pure(t)
//
//        def map[B](fx: T => B)(implicit monad: Monad[F]): F[B] = monad.map(n)(fx)
//
//    }
//}

trait ModularCalc[F[_]] {
    def generateVal(): F[Int]
    def doubleVal(n: Int): F[Int]
}

class ModularCalcImpl[F[_]](implicit monad: Applicative[F]) extends ModularCalc[F] {
    override def generateVal(): F[Int] = {
        val v = Random.nextInt(100)
        println(v)
        monad.pure(v)
    }

    override def doubleVal(n: Int): F[Int] = monad.pure(n * 2)
}

object ModularProgram {
    def run() = {

        import cats.instances.future._
        import scala.concurrent.ExecutionContext.Implicits.global

        val calc = new ModularCalcImpl

        for {
            n <- calc.generateVal()
            d <- calc.doubleVal(n)
        } yield d
    }
}

val eventualResult = ModularProgram.run()
println("After run()")

import scala.concurrent.duration._
Await.result(eventualResult, 3.seconds)