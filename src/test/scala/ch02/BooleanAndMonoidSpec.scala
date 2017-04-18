package ch02

import org.scalatest.FlatSpec
import Monoid.BooleanAndMonoid
import org.scalatest.prop.Checkers

class BooleanAndMonoidSpec extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

  implicit val monoid: Monoid[Boolean] = BooleanAndMonoid

  "Combine operations" should "be associative" in {
    check(checkAssociativity)
  }

  "Empty element properties" should "hold" in {
    check(checkEmptyElementProperties)
  }
}
