package ch02

import org.scalatest.FlatSpec
import Semigroup.BooleanXORSemigroup
import org.scalatest.prop.Checkers

class BooleanXORSemigroupSpec extends FlatSpec with Checkers
  with AssociativityChecker[Boolean] {

    implicit val semigroup: Semigroup[Boolean] = BooleanXORSemigroup

  "Combine operations" should "be associative" in {
    check(checkAssociativity)
  }
}
