package ch3

import cats.Functor

import scala.language.implicitConversions

/**
  *
  */
sealed trait Result[+A] // defined trait Result
final case class Success[A](value: A) extends Result[A] // defined class Success
final case class Warning[A](value: A, message: String) extends Result[A] // defined class Warning
final case class Failure(message: String) extends Result[Nothing] // defined class Failure

object ResultFunctor extends Functor[Result] {
    override def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = {
        fa match {
            case Success(a) => Success(f(a))
            case Warning(a, msg) => Warning(f(a), msg)
            case f: Failure => f
        }
    }
}