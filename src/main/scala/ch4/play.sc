import cats.Monad

import scala.language.higherKinds

def flatMap[F[_], A, B](value: F[A])(func: A => F[B]): F[B] = ???

def pure[F[_], A](value: A): F[A] = ???

def map[F[_], A, B](value: F[A])(fx: A => B): F[B] =
    flatMap(value)(a => pure(fx(a)))

val optionMonad = new Monad[Option] {
    override def flatMap[A, B](fa: Option[A])(f: (A) => Option[B]): Option[B] =
        fa flatMap f

    override def pure[A](x: A): Option[A] = Some(x)

    override def tailRecM[A, B](a: A)(f: (A) => Option[Either[A, B]]): Option[B] =
        f(a) match {
            case Some(Left(aa)) => tailRecM(aa)(f)
            case Some(Right(bb)) => Some(bb)
            case None => None
        }
}

import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.syntax.applicative._

def sumSquare[A[_] : Monad](a: Int, b: Int): A[Int] = {
    val ma: A[Int] = pure(a)
    val mb: A[Int] = pure(b)

    ma.flatMap(av => mb.map(bv => av * av + bv * bv))
}

import cats.instances.option._
import cats.instances.list._

sumSquare[Option](3, 2)
//sumSquare[List](3, 4)