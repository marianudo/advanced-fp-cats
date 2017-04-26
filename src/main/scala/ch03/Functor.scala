package ch03

import scala.language.higherKinds

trait Invariant[F[_]] {
  def imap[A, B](fa: F[A])(a: A => B)(b: B => A): F[B]
}

trait Contravariant[F[_]] extends Invariant[F] {
  def contramap[A, B](fa: F[A])(f: B => A): F[B]

  override def imap[A, B](fa: F[A])(a: (A) => B)(b: (B) => A): F[B] =
    contramap(fa)(b)
}

trait Functor[F[_]] extends Invariant[F] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  override def imap[A, B](fa: F[A])(a: (A) => B)(b: (B) => A): F[B] =
    map(fa)(a)
}
