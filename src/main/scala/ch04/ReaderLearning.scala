package ch04

import cats.data.Reader
import cats.syntax.applicative._

object ReaderLearning {
  case class Db(
                 usernames: Map[Int, String],
                 passwords: Map[String, String]
               )

  type DbReader[T] = Reader[Db, T]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(_.usernames.get(userId))

  def checkPassword(
                     username: String,
                     password: String
                   ): DbReader[Boolean] =
    Reader(_.passwords.get(username).contains(password))

  def checkLogin(
                  userId: Int,
                  password: String
                ): DbReader[Boolean] = {

    for {
      un <- findUsername(userId)
      loginCorrect <- un.map(str => checkPassword(str, password)).getOrElse(false.pure[DbReader])
    } yield loginCorrect
  }
}
