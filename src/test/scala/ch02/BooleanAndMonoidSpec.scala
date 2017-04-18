package ch02

import org.scalatest.FlatSpec
import Monoid.BooleanAndMonoid
import org.scalatest.prop.Checkers

class BooleanAndMonoidSpec extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

  "Combine operations" should "be associative" in {
    check(checkAssociativity(BooleanAndMonoid))
  }

  "Empty element properties" should "hold" in {
    check(checkEmptyElementProperties(BooleanAndMonoid))
  }
}
