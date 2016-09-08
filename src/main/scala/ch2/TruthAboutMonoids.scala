package ch2

/**
  *
  */
trait Semigroup[T] {
    def combine(x: T, y: T): T
}

trait Monoid[T] extends Semigroup[T] {
    def empty: T
}

object Monoid {
    def apply[T](implicit monoid: Monoid[T]) = monoid
}

object BooleanAndMonoid extends Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
}

object BooleanOrMonoid extends Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
}

object BooleanXorMonoid extends Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
}

object BooleanXnorMonoid extends Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
}