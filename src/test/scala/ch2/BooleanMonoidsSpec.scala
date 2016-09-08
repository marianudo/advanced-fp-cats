package ch2

import org.scalatest.FunSpec

/**
  *
  */
class BooleanMonoidsSpec extends FunSpec {

    def assertAssociativity(monoid: Monoid[Boolean]): Unit = {
        def testAssociativity(b1: Boolean, b2: Boolean, b3: Boolean): Boolean = {
            val _b1 = monoid.combine(b1, monoid.combine(b2, b3))
            val _b2 = monoid.combine(monoid.combine(b1, b2), b3)
            _b1 === _b2
        }

        assert(testAssociativity(true, true, true))
        assert(testAssociativity(true, false, true))
        assert(testAssociativity(true, true, false))
        assert(testAssociativity(false, true, true))
        assert(testAssociativity(false, false, true))
        assert(testAssociativity(false, true, false))
        assert(testAssociativity(false, false, false))
    }

    def assertIdentity(implicit monoid: Monoid[Boolean]): Unit = {
        def testIdentity(b: Boolean)(implicit monoid: Monoid[Boolean]): Boolean = monoid.combine(b, monoid.empty)

        assert(testIdentity(true))
        assert(!testIdentity(false))
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
}
