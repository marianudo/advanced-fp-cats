package ch02

import ch02.Monoid.SetIntersectionSemigroup
import org.scalatest.FunSpec
import org.scalatest.prop.Checkers

class SetIntersectionSemigroupSpec extends FunSpec with Checkers {
  describe("A set of Strings") {

    implicit val monoid: Semigroup[Set[String]] = new SetIntersectionSemigroup[String]

    val propertiesChecker = new AssociativityChecker[Set[String]] {}

    it("fulfils the associativity property") {
      check(propertiesChecker.checkAssociativity)
    }
  }
}
