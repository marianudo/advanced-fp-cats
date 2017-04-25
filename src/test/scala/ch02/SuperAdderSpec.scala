package ch02

import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

class SuperAdderSpec extends FlatSpec with Checkers with AssociativityChecker[Int] {
  "A simple list of integers " should "combine as expected" in {
    import ch02.SuperAdder.add

    assert(add(List(1, 2, 3, 4, 5)) == 15)
  }
}
