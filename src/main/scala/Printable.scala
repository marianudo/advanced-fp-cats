trait Printable[A] {
  def format(a: A): String
}

object PrintableInstances {
  implicit val printableString = new Printable[String] {
    override def format(s: String) = s
  }

  implicit val printableInt = new Printable[Int] {
    override def format(i: Int) = i.toString
  }
}

object Printable {
  def format[A](a: A)(implicit pa: Printable[A]): String =
    pa.format(a)

  def print[A](a: A)(implicit pa: Printable[A]): Unit =
    println(format(a))
}
