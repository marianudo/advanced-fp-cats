package ch02

object SuperAdder {
  def add(items: List[Int]): Int = {
    import cats.Monoid
    import cats.instances.int._
    import cats.syntax.monoid._

    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }
}
