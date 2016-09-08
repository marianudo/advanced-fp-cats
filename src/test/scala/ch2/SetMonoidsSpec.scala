package ch2

import ch2.Monoid.{checkAssociativity, checkIdentity}
import org.scalatest.FunSpec

/**
  *
  */
class SetMonoidsSpec extends FunSpec {

    def assertAssociativity(implicit monoid: Monoid[Set[Int]]): Unit = {
        assert(checkAssociativity(Set(1), Set(2), Set(3)))
    }

    def assertIdentity(implicit monoid: Monoid[Set[Int]]): Unit = {
        assert(checkIdentity(Set(3, 4, 5)))
    }

    describe("A Set Monoid") {
        implicit val monoid: Monoid[Set[Int]] = new SetMonoid()

        it("obeys the associativity rule") {
            assertAssociativity
        }

        it("Obeys the identity element rule") {
            assertIdentity
        }
    }
}
