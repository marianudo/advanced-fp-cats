package ch06

object FormValidation {

  case class User(name: String, age: Int)

  import cats.data.Validated

  type FormData = Map[String, String]
  type ErrorsOr[A] = Either[List[String], A]
  type AllErrorsOr[A] = Validated[List[String], A]

  import cats.syntax.either._

  def getValue(name: String)(formData: FormData): ErrorsOr[String] =
    formData.get(name).toRight(List(s"Parameter $name is not part of form data"))

  def parseInt(value: String): ErrorsOr[Int] =
    value.asRight
      .flatMap(s => Either.catchOnly[NumberFormatException](s.toInt))
      .leftMap(_ => List("Cannot convert data to Int"))

  def nonBlank(str: String): ErrorsOr[String] =
    str.asRight
      .ensure(List("Name must be non-empty"))(_.trim.nonEmpty)

  def nonNegative(i: Int): ErrorsOr[Int] =
    i.asRight
      .ensure(List("Age should be not negative"))(_ >= 0)

  def readName(name: String)(formData: FormData): ErrorsOr[String] =
    for {
      value <- getValue(name)(formData)
      nb <- nonBlank(value)
    } yield nb

  def readAge(name: String) (parameters: FormData): Either[List[String], Int] =
    for {
      v <- getValue(name)(parameters)
      i <- parseInt(v)
      n <- nonNegative(i)
    } yield n
}
