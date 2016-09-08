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

    def testAssociativity[T](x: T, y: T, z: T)(implicit monoid: Monoid[T]): Boolean = {
        val _b1 = monoid.combine(x, monoid.combine(y, z))
        val _b2 = monoid.combine(monoid.combine(x, y), z)
        _b1 == _b2
    }

    def testIdentity[T](x: T)(implicit monoid: Monoid[T]): Boolean = {
        monoid.combine(x, monoid.empty) == x
    }
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

class SetMonoid[T] extends Monoid[Set[T]] {
    override def empty: Set[T] = Set.empty[T]

    override def combine(x: Set[T], y: Set[T]): Set[T] = x ++ y
}