package ch3

import cats.syntax.functor._
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
class ResultFunctorSpec extends FlatSpec with Matchers {

    implicit val functor = ResultFunctor

    def success[A](value: A): Result[A] = Success(value)

    def warning[A](value: A, message: String): Result[A] = Warning(value, message)

    def failure[A](message: String): Result[A] = Failure(message)

    "A Success result" should "be mapped appropriately" in {
        success("It's Ok") map (_ + ", man") shouldBe Success("It's Ok, man")
    }

    "A Warning result" should "be mapped appropriately" in {
        warning(100, "We might have lost precision") map (_ % 2) shouldBe Warning(0, "We might have lost precision")
    }

    "A Failure result" should "be mapped appropriately" in {
        failure("Nah to the ah to the no, no, no") map "pepe" shouldBe Failure("Nah to the ah to the no, no, no")
    }
}
