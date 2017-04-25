package ch02

import ch02.SuperAdder.Order
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

class SuperAdderSpec extends FlatSpec with Checkers with AssociativityChecker[Int] {
  "A simple list of integers" should "combine as expected" in {
    import ch02.SuperAdder.add

    assert(add(List(1, 2, 3, 4, 5)) == 15)
  }

  import ch02.SuperAdder.genericCombine

  "A list of integers" should "combine with the generic combiber" in {
    import cats.instances.int._
    assert(genericCombine(List(1, 2, 3, 4, 5)) == 15)
  }

  "A list of strings" should "combine with the generic combiner" in {
    import cats.instances.string._
    assert(genericCombine(List("a", "b", "c")) == "abc")
  }

  "A list of optional ints" should "combine as expected" in {
    import ch02.SuperAdder.addMaybes

    assert(addMaybes(List(Some(2), Some(4), None, Some(4))).contains(10))
  }

  "A sequence of orders" should "add up right" in {
    import ch02.SuperAdder.addUpOrders

    assert(
      addUpOrders
      (
        List(
          Order(2.0, 1.0),
          Order(1.1, 2.2)
        )
      ) == Order(3.1, 3.2)
    )
  }
}
