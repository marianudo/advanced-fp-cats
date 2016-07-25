package ch1

import cats.Show

/**
  *
  */
object PrintableWithShow extends App {

    case class Cat(name: String, age: Int, color: String)

    import cats.implicits.stringShow
    import cats.implicits.intShow

    import cats.syntax.show._

    implicit val catShow: Show[Cat] = Show.show[Cat] { cat =>
        s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat"
    }

    println(Cat("Misifú", 3, "Brown").show)
}
