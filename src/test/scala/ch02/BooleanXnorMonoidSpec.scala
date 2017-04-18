package ch02

import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers
import Monoid.BooleanXnorMonoid

class BooleanXnorMonoid extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

  implicit val monoid: Monoid[Boolean] = BooleanXnorMonoid

  "Combine operations" should "be associative" in {
      check(checkAssociativity)
  }

  "Empty element properties" should "hold" in {
      check(checkEmptyElementProperties)
  }
}
