package ch01

import cats.Show
import cats.syntax.show._

object CatExampleWithCats {
  implicit val showCatTypeClass =
    Show.show[Cat](cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat")
}
