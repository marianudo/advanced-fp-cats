package ch02

import org.scalatest.FlatSpec
import Monoid.BooleanAndMonoid

class BooleanAndMonoidSpec extends FlatSpec with AssociativityChecker {
  "Combine operations" should "be associative" in {
    checkAssociativity(BooleanAndMonoid)
  }

  "Empty element properties" should "hold" in {
    checkEmptyElementProperties(BooleanAndMonoid)
  }
}
