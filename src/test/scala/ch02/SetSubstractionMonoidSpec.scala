package ch02

import org.scalatest.FunSpec
import org.scalatest.prop.Checkers
import ch02.Monoid.SetSubstractMonoid

class SetSubstractionMonoidSpec extends FunSpec with Checkers {

  describe("A set of Strings") {
    implicit val monoid: Monoid[Set[String]] = new SetSubstractMonoid[String]

    val propertiesChecker = new AssociativityChecker[Set[String]] {}

    it("fulfils the associativity property") {
      check(propertiesChecker.checkAssociativity)
    }

    it("fulfils the empty element property") {
      check(propertiesChecker.checkEmptyElementProperties)
    }
  }
}
