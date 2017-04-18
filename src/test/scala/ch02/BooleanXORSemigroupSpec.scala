package ch02

import org.scalatest.FlatSpec
import Semigroup.BooleanXORSemigroup

class BooleanXORSemigroupSpec extends FlatSpec with AssociativityChecker {
  "Combine operations" should "be associative" in {
    checkAssociativity(BooleanXORSemigroup)
  }
}
