package ch4

import cats.syntax.flatMap._
import cats.syntax.functor._
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
class ResultMonadSpec extends FlatSpec with Matchers {

    implicit val monad = ResultMonad

    def success[A](value: A): Result[A] = Success(value)

    def warning[A](value: A, message: String): Result[A] = Warning(value, message)

    def failure[A](message: String): Result[A] = Failure(message)

    "A Success result" should "be flat mapped appropriately" in {
        success(45) flatMap (Success(_)) shouldBe Success(45)
    }

    "A Warning result" should "be flat mapped appropriately" in {
        warning(45, "almost") flatMap (a => Warning(a, "Uy")) shouldBe Warning(45, "almost; Uy")
        warning(45, "almost") flatMap (Success(_)) shouldBe Warning(45, "almost")
    }

    "A Failure result" should "be flat mapped appropriately" in {
        failure("Nah to the ah to the no, no, no") flatMap ((a: Int) => Warning(a, "nop")) shouldBe Failure("Nah to the ah to the no, no, no")
    }

    "A Result instance" should "can be used in a for comprehension" in {
        val result = for {
            a <- success(45)
            b <- warning(a, "Almost")
        } yield b

        result shouldBe Warning(45, "Almost")

        val result2 = for {
            a <- success(1)
            b <- warning(2, "Message1")
            c <- warning(a + b, "Message2")
        } yield c * 10

        result2 shouldBe Warning(30,"Message1; Message2")
    }
}
