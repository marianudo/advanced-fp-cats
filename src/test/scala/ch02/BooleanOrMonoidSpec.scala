package ch02

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import Monoid._
import BooleanOrMonoid._

class BooleanOrMonoidSpec extends FlatSpec with GeneratorDrivenPropertyChecks with Matchers {
  "Combine operations" should "be associative" in {
    forAll {
      (b1: Boolean, b2: Boolean, b3: Boolean) => {
        combine(b1, combine(b2, b3)) should be (combine(combine(b1, b2), b3))
      }
    }
  }

  "Empty element properties" should "hold" in {
    forAll {
      (b: Boolean) => {
        assert(
          combine(b, BooleanOrMonoid.empty) === combine(BooleanOrMonoid.empty, b)
        )
        assert(
          combine(b, BooleanOrMonoid.empty) === b
        )
      }
    }
  }
}
