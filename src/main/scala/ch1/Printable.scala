package ch1

/**
  *
  */
trait Printable[A] {
    def format(a: A): String
}

object Print {
    def format[A](value: A)(implicit printable: Printable[A]): String =
        printable.format(value)

    def print[A](value: A)(implicit printable: Printable[A]): Unit =
        println(format(value))
}

final case class Cat(name: String, age: Int, color: String)

object PrintDefaults {
    implicit val stringPrintable = new Printable[String] {
        override def format(a: String): String = a
    }

    implicit val intPrintable = new Printable[Int] {
        override def format(a: Int): String = a.toString
    }

    implicit val catPrintable = new Printable[Cat] {
        override def format(a: Cat): String =
            s"${Print.format(a.name)} is a ${Print.format(a.age)} year-old ${Print.format(a.color)} cat"
    }
}

object PrintSyntax {
    implicit class PrintOps[A](a: A) {
        def format(implicit printable: Printable[A]): String =
            printable.format(a)

        def print(implicit printable: Printable[A]): Unit =
            println(format)
    }
}

object Main extends App {
    val cat = Cat("Misifú", 3, "Brown")

    import PrintDefaults._
    import PrintSyntax._

    cat.print
}