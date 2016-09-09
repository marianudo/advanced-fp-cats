import cats.Monoid

def add[A: Monoid](items: List[A]): A = {
    items.foldLeft(Monoid[A].empty)(Monoid[A].combine)
}

import cats.instances.int._
import cats.instances.option._
import cats.instances.string._

add(List(Option(1), Option(2), None, Some(4), None, Some(6)))
add(List(1, 2, 3, 4, 5, 6, 7))
add(List("Pepe ", "Se ", "fue ", "a ", "pescar"))

case class Order(totalCost: Double, quantity: Double)

implicit val orderMonoid = new Monoid[Order] {
    override def empty: Order = Order(0.0, 0.0)

    override def combine(x: Order, y: Order): Order =
        Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
}

add(List(Order(1.2, 2.2), Order(2.3, 4.5)))

import cats.syntax.option._
none[Int]
1.some