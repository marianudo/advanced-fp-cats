package ch03

trait Printable[A] {
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = {
    val self = this

    new Printable[B] {
      override def format(value: B): String = self.format(func(value))
    }
  }
}
