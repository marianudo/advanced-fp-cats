package ch06

import cats.data.Validated
import cats.syntax.either._
import cats.syntax.cartesian._
import cats.instances.list._

object FormValidation {
  case class User(name: String, age: Int)

  type FormData = Map[String, String]
  type ErrorsOr[A] = Either[List[String], A]
  type AllErrorsOr[A] = Validated[List[String], A]

  def getValue(name: String)(data: FormData): ErrorsOr[String] =
    Either.fromOption(data.get(name), List(s"$name not present in the form"))

  val nameKey = "name"
  val ageKey = "age"

  val getName = getValue(nameKey) _
  val getAge = getValue(ageKey) _

  def parseInt(name: String)(data: String): ErrorsOr[Int] =
    Either.catchOnly[NumberFormatException](data.toInt)
      .leftMap(_ => List(s"$name must be an integer"))

  def nonBlank(name: String)(data: String): ErrorsOr[String] =
    Right(data).ensure(List(s"$name cannot be blank"))(_.nonEmpty)

  def nonNegative(name: String)(data: Int): ErrorsOr[Int] =
    Right(data).ensure(List(s"$name cannot be negative"))(_ > 0)

  def readName(form: FormData): ErrorsOr[String] =
    getName(form).flatMap(nonBlank(nameKey))

  def readAge(form: FormData): ErrorsOr[Int] =
    for {
      ageAsStr <- getAge(form)
      ageAsInt <- parseInt(ageKey)(ageAsStr)
      result <- nonNegative(ageKey)(ageAsInt)
    } yield result

  def readUser(form: FormData): AllErrorsOr[User] =
    (readName(form).toValidated |@| readAge(form).toValidated).map(User)
}
