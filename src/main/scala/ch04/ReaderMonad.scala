package ch04

import cats.data.{Kleisli, Reader}

object ReaderMonad {
  /*
   * From the book
   */
  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(_.usernames.get(userId))

  def checkPassword(
     username: String,
     password: String
   ): DbReader[Boolean] = Reader(_.passwords.get(username).contains(password))

  def checkLogin(
    userId: Int,
    password: String
  ): DbReader[Boolean] = {
    import cats.syntax.applicative._

    for {
      maybeUserName <- findUsername(userId)
      pwdOk <- maybeUserName.map(userName => checkPassword(userName, password)).getOrElse(false.pure[DbReader])
    } yield pwdOk
  }

  /*
   * On my own
   */

  val parse: String => Option[Int] =
    s => if (s.matches("-?[0-9]+")) Some(s.toInt) else None

  object ExampleParseReciprocal {
    val reciprocal: Int => Option[Double] =
      i => if (i != 0) Some(1.0 / i) else None

    import cats.instances.option._ // Bring in FlatMap[Option]
    val parseReciprocal: Kleisli[Option, String, Double] = Kleisli(reciprocal).compose(parse)
  }

  object ExampleParseHalf {
    val half: Int => Option[Int] = i => if(i % 2 == 0) Some(i / 2) else None

    import cats.instances.option._
    val parseHalf: Kleisli[Option, String, Int] = Kleisli(half).compose(parse)
    val parseHalf2: Kleisli[Option, String, Int] = Kleisli(parse).andThen(half)
  }

  object ExampleParseIsEven {
    val isEven: Int => Boolean = _ % 2 == 0
    val liftedIsEven: Int => Option[Boolean] = i => Some(isEven(i))

    import cats.instances.option._
    val parseIsEven: Kleisli[Option, String, Boolean] = Kleisli(liftedIsEven).compose(parse)
  }
}
