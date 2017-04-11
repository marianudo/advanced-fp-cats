package ch01

final case class Cat(
  name: String,
  age: Int,
  color: String
)

object PrintableCatInstances {
  implicit val printableCat = new Printable[Cat] {
    override def format(cat: Cat) =
      s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }
}

object PrintableSyntax {
  implicit class PrintableSyntaxClass[A](a: A) {
    def format(implicit p: Printable[A]): String =
      p.format(a)
  }
}
