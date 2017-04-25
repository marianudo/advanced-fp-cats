package ch02

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

object Semigroup {
  object BooleanXORSemigroup extends Semigroup[Boolean] {
    def combine(b1: Boolean, b2: Boolean): Boolean = b1 ^ b2
  }
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

  object BooleanOrMonoid extends Monoid[Boolean] {
    def combine(b1: Boolean, b2: Boolean): Boolean = b1 || b2

    val empty = false
  }

  object BooleanEitherMonoid extends Monoid[Boolean] {
    def combine(b1: Boolean, b2: Boolean): Boolean =
      (b1 && !b2) || (!b1 && b2)

    val empty = false
  }

  object BooleanXnorMonoid extends Monoid[Boolean] {
    def combine(b1: Boolean, b2: Boolean): Boolean =
      (!b1 || b2) && (b1 || !b2)

    val empty = true
  }

  class SetUnionMonoid[T] extends Monoid[Set[T]] {
    override def empty: Set[T] = Set.empty[T]

    override def combine(x: Set[T], y: Set[T]): Set[T] = x ++ y
  }

  class SetIntersectionSemigroup[T] extends Semigroup[Set[T]] {
    override def combine(x: Set[T], y: Set[T]): Set[T] = x.intersect(y)
  }
}
