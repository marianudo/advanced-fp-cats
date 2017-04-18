package ch02

import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers

import Monoid.BooleanEitherMonoid

class BooleanEitherMonoidSpec extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

  "Combine operations" should "be associative" in {
      check(checkAssociativity(BooleanEitherMonoid))
  }

  "Empty element properties" should "hold" in {
      check(checkEmptyElementProperties(BooleanEitherMonoid))
  }
}
