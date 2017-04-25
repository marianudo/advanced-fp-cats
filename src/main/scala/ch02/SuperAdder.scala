package ch02

object SuperAdder {
  def add(items: List[Int]): Int = {
    import cats.Monoid
    import cats.instances.int._
    import cats.syntax.semigroup._

    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }

  def genericCombine[T](items: Seq[T])(implicit monoid: cats.Monoid[T]) = {
    items.foldLeft(monoid.empty)(monoid.combine)
  }
}
