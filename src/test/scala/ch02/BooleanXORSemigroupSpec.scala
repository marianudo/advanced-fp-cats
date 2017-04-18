package ch02

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import Semigroup._
import BooleanXORSemigroup.combine

class BooleanXORSemigroupSpec extends FlatSpec with GeneratorDrivenPropertyChecks with Matchers {
  "Combine operations" should "be associative" in {
    forAll {
      (b1: Boolean, b2: Boolean, b3: Boolean) => {
        combine(b1, combine(b2, b3)) should be (combine(combine(b1, b2), b3))
      }
    }
  }
}
