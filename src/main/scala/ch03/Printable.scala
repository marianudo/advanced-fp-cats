package ch03

trait Printable[A] {
  def format(value: A): String

  private val outerFormat = format _

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      override def format(value: B): String = outerFormat(func(value))
    }
}
