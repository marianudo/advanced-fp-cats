package ch02

import org.scalatest.FlatSpec
import Monoid.BooleanOrMonoid
import org.scalatest.prop.Checkers

class BooleanOrMonoidSpec extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

  "Combine operations" should "be associative" in {
      check(checkAssociativity(BooleanOrMonoid))
  }

  "Empty element properties" should "hold" in {
      check(checkEmptyElementProperties(BooleanOrMonoid))
  }
}
