package ch02

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid

  object BooleanAndMonoid extends Monoid[Boolean] {
    def combine(b1: Boolean, b2: Boolean): Boolean = b1 && b2

    val empty = true
  }
}
