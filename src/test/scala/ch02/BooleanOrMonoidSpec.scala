package ch02

import org.scalatest.FlatSpec
import Monoid.BooleanOrMonoid
import BooleanOrMonoid._

class BooleanOrMonoidSpec extends FlatSpec with AssociativityChecker {
  "Combine operations" should "be associative" in {
    checkAssociativity(BooleanOrMonoid)
  }

  "Empty element properties" should "hold" in {
    checkEmptyElementProperties(BooleanOrMonoid)
  }
}
