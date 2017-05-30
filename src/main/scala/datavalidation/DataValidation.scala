package datavalidation

import cats.Semigroup
import cats.syntax.semigroup._
import cats.syntax.either._

object DataValidation {
//  type Check[E, A] = A => Either[E, A]

  object WithEither {

    sealed trait Check[E, A] {

      def apply(a: A)(implicit sg: Semigroup[E]): Either[E, A] =
        this match {
          case Pure(fx) => fx(a)
          case And(c1, c2) => (c1(a), c2(a)) match {
            case (Left(e1), Left(e2)) => (e1 |+| e2).asLeft
            case (Right(_), Left(e)) => e.asLeft
            case (Left(e), Right(_)) => e.asLeft
            case (Right(_), Right(_)) => a.asRight
          }
        }

      def and(that: Check[E, A]): Check[E, A] = And(this, that)
    }

    case class And[E, A](left: Check[E, A], right: Check[E, A]) extends Check[E, A]

    case class Pure[E, A](fx: A => Either[E, A]) extends Check[E, A]

  }

  object WithValidated {
    // TODO
  }
}
