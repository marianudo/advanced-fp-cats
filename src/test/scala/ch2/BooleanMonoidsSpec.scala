package ch2

import ch2.Monoid.{checkAssociativity, checkIdentity}
import org.scalatest.{FunSpec, Matchers}

/**
  *
  */
class BooleanMonoidsSpec extends FunSpec with Matchers {

    def assertAssociativity(implicit monoid: Monoid[Boolean]): Unit = {
        checkAssociativity(true, true, true) shouldBe true
        checkAssociativity(true, false, true) shouldBe true
        checkAssociativity(true, true, false) shouldBe true
        checkAssociativity(false, true, true) shouldBe true
        checkAssociativity(false, false, true) shouldBe true
        checkAssociativity(false, true, false) shouldBe true
        checkAssociativity(false, false, false) shouldBe true
    }

    def assertIdentity(implicit monoid: Monoid[Boolean]): Unit = {
        checkIdentity(true) shouldBe true
        checkIdentity(false) shouldBe true
    }

    describe("A Boolean And monoid") {
        it("obeys the associativity rule") {
            assertAssociativity(BooleanAndMonoid)
        }

        it("Obeys the identity element rule") {
            assertIdentity(BooleanAndMonoid)
        }
    }

    describe("A Boolean Or monoid") {
        it("obeys the associativity rule") {
            assertAssociativity(BooleanOrMonoid)
        }

        it("Obeys the identity element rule") {
            assertIdentity(BooleanOrMonoid)
        }
    }

    describe("A Boolean XOR monoid") {
        it("obeys the associativity rule") {
            assertAssociativity(BooleanXorMonoid)
        }

        it("Obeys the identity element rule") {
            assertIdentity(BooleanXorMonoid)
        }
    }

    describe("A boolean XNOR monod") {
        it("obeys the associativity rule") {
            assertAssociativity(BooleanXnorMonoid)
        }

        it("Obeys the identity element rule") {
            assertIdentity(BooleanXnorMonoid)
        }
    }
}
