package ch1

/**
  *
  */
object CatShow extends App {
    import cats.Show

    final case class Cat(name: String, age: Int, color: String)

    import cats.syntax.show._

    implicit val catShow = Show.show[Cat] { cat =>
        s"${cat.name} is a ${cat.age} years old ${cat.color} cat"
    }

    val misifu = Cat("Misifu", 3, "black")

    println(misifu.show)
}
