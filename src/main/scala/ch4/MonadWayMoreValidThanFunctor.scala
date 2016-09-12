package ch4

/**
  *
  */
sealed trait Result[+A] // defined trait Result
final case class Success[A](value: A) extends Result[A] // defined class Success
final case class Warning[A](value: A, message: String) extends Result[A] // defined class Warning
final case class Failure(message: String) extends Result[Nothing] // defined class Failure

import cats.Monad

object ResultMonad extends Monad[Result] {
    override def flatMap[A, B](fa: Result[A])(f: (A) => Result[B]): Result[B] = fa match {
        case Success(a) => f(a)
        case Warning(a, msg) => f(a) match {
            case Success(aa) => Warning(aa, msg)
            case Warning(aa, msg2) => Warning(aa, s"$msg; $msg2")
            case f: Failure => f
        }
        case f: Failure => f
    }

    override def tailRecM[A, B](a: A)(f: (A) => Result[Either[A, B]]): Result[B] = ???

    override def pure[A](x: A): Result[A] = Success(x)
}
