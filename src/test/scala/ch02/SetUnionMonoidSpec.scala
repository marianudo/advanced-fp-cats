package ch02

import ch02.Monoid.SetUnionMonoid
import org.scalatest.FunSpec
import org.scalatest.prop.Checkers

class SetUnionMonoidSpec extends FunSpec with Checkers {

  describe("A set of strings") {
    implicit val monoid: Monoid[Set[String]] = new SetUnionMonoid[String]

    val propertiesChecker = new AssociativityChecker[Set[String]] {}

    it("fulfils the associativity property") {
      check(propertiesChecker.checkAssociativity)
    }

    it("fulfils the empty element property") {
      check(propertiesChecker.checkEmptyElementProperties)
    }
  }

  describe("A Set of ints") {
    val propertiesChecker = new AssociativityChecker[Set[Int]] {}

    implicit val monoid: Monoid[Set[Int]] = new SetUnionMonoid[Int]

    it("fulfils the associativity property") {
      check(propertiesChecker.checkAssociativity)
    }

    it("fulfils the empty element property") {
      check(propertiesChecker.checkEmptyElementProperties)
    }
  }
}
