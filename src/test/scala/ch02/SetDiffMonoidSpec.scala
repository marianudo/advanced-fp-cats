package ch02

import ch02.Monoid.SetDiffMonoid
import org.scalatest.FunSpec
import org.scalatest.prop.Checkers

class SetDiffMonoidSpec extends FunSpec with Checkers {

  describe("A set of strings") {
    implicit val monoid: Monoid[Set[String]] = new SetDiffMonoid

    val propertiesChecker = new AssociativityChecker[Set[String]] {}

    it("fulfils the associativity property") {
      check(propertiesChecker.checkAssociativity)
    }

    it("fulfils the empty element property") {
      check(propertiesChecker.checkEmptyElementProperties)
    }
  }
}
