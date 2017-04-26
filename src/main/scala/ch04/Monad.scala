package ch04

import ch03.Functor

import scala.language.higherKinds

trait Monad[F[_]] extends Functor[F] {

  def point[A](a: A): F[A]

  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    flatMap(fa)(a => point(f(a)))
}
