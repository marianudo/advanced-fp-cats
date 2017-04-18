package ch02

import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

trait AssociativityChecker extends GeneratorDrivenPropertyChecks with Matchers {

  def checkAssociativity(implicit semigroup: Semigroup[Boolean]) = forAll {
    (b1: Boolean, b2: Boolean, b3: Boolean) => {
      semigroup.combine(b1, semigroup.combine(b2, b3)) should be (semigroup.combine(semigroup.combine(b1, b2), b3))
    }
  }

  def checkEmptyElementProperties(implicit monoid: Monoid[Boolean]) = forAll {
    (b: Boolean) => {
      assert(
        monoid.combine(b, monoid.empty) == b &&
        monoid.combine(monoid.empty, b) == b
      )
    }
  }
}
