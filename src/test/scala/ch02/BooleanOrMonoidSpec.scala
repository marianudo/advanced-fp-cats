package ch02

import org.scalatest.FlatSpec
import Monoid.BooleanOrMonoid
import org.scalatest.prop.Checkers

class BooleanOrMonoidSpec extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

  implicit val monoid: Monoid[Boolean] = BooleanOrMonoid

  "Combine operations" should "be associative" in {
      check(checkAssociativity)
  }

  "Empty element properties" should "hold" in {
      check(checkEmptyElementProperties)
  }
}
