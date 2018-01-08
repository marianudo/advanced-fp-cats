package ch07

import cats.{Applicative, Foldable, Monad, Monoid}

import scala.language.higherKinds

object FoldableAndTraverse {
  object TraverseWithFutures {

    import scala.concurrent._
    import scala.concurrent.duration._
    import scala.concurrent.ExecutionContext.Implicits.global

    val hostnames = List(
      "alpha.example.com",
      "beta.example.com",
      "gamma.demo.com"
    )

    def getUptime(hostname: String): Future[Int] =
      Future(hostname.length * 60) // just for demonstration

    val eventualUptimes: Future[List[Int]] = {
      hostnames.map(getUptime).foldLeft(Future(List.empty[Int]))(
        (acc: Future[List[Int]], nextFut: Future[Int]) =>
          for {
            acc0 <- acc
            nextFut0 <- nextFut
          } yield nextFut0 :: acc0
      )

    }

    def futureTraverse[A, B](la: List[A])(fx: A => Future[B]): Future[List[B]] =
      la.foldLeft(Future(List.empty[B])) { (acc, a) =>
        for {
          acc0 <- acc
          b <- fx(a)
        } yield b :: acc0
      }
  }

  object GenericTraverse {

    def listTraverseWithMonad[A, B, G[_]: Monad](la: List[A])(fx: A => G[B]): G[List[B]] = {
      import cats.syntax.applicative._
      import cats.syntax.flatMap._
      import cats.syntax.functor._

      la.foldLeft(List.empty[B].pure[G])((listBInG, a) => {
        for {
          listB <- listBInG
          b <- fx(a)
        } yield listB :+ b
      })
    }

    def listTraverse[A, B, G[_]: Applicative](la: List[A])(fx: A => G[B]): G[List[B]] = {
      import cats.syntax.applicative._
      import cats.syntax.cartesian._

      la.foldLeft(List.empty[B].pure[G])((listBInG, a) => {
        (listBInG |@| fx(a)).map(_ :+ _)
      })
    }

    def listSequence[P[_]: Applicative, A](la: List[P[A]]): P[List[A]] =
      listTraverse(la)(identity)
  }

  object TraverseWithVectors {

  }
}

/*
 F[G[_]] => G[F[_]]
 import cats.data.Validated
import cats.instances.list._
type ErrorsOr[A] = Validated[List[String], A]
def process(inputs: List[Int]): ErrorsOr[List[Int]] =
  listTraverse(inputs) { n =>
    if(n % 2 == 0) {
      Validated.valid(n)
    } else {
      Validated.invalid(List(s"$n is not even"))
} }
 */
