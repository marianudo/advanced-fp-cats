package datavalidation

import cats.Semigroup
import cats.syntax.semigroup._

object DataValidation {
//  type Check[E, A] = A => Either[E, A]

  sealed trait Check[E, A] {

    def apply(a: A): Either[E, A]

    def and(that: Check[E, A])(implicit sg: Semigroup[E]): Check[E, A]
  }

  case class CheckF[E, A](fx: A => Either[E, A]) extends Check[E, A] {

    override def apply(a: A) = fx(a)

    override def and(that: Check[E, A])(implicit sg: Semigroup[E]): Check[E, A] =
      CheckF { a =>
        (this(a), that(a)) match {
          case (Left(e1), Left(e2)) => Left(e1 |+| e2)
          case (Left(e), Right(_)) => Left(e)
          case (Right(_), Left(e)) => Left(e)
          case (Right(_), Right(_)) => Right(a)
        }
      }
  }
}
