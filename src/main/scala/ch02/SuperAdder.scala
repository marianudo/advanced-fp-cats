package ch02

object SuperAdder {
  def add(items: List[Int]): Int = {
    import cats.instances.int._

    genericCombine(items)
  }

  def genericCombine[T](items: Seq[T])(implicit monoid: cats.Monoid[T]) = {
    items.foldLeft(monoid.empty)(monoid.combine)
  }

  def addMaybes(items: List[Option[Int]]): Option[Int] = {
    import cats.instances.int._
    import cats.instances.option._

    genericCombine(items)
  }

  case class Order(totalCost: Double, quantity: Double)

  def addUpOrders(orders: Seq[Order]): Order = ???
}
