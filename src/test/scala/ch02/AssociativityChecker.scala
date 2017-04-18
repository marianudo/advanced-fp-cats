package ch02

trait AssociativityChecker[T] {

  def checkAssociativity(implicit semigroup: Semigroup[T]): (T, T, T) => Boolean =
    (b1: T, b2: T, b3: T) => {
      semigroup.combine(b1, semigroup.combine(b2, b3)) == semigroup.combine(semigroup.combine(b1, b2), b3)
    }

  def checkEmptyElementProperties(implicit monoid: Monoid[T]): T => Boolean =
    (b: T) => {
        monoid.combine(b, monoid.empty) == b &&
        monoid.combine(monoid.empty, b) == b
    }
}
