package ch04

import ch04.IdDiscoveries.Identity

object IdDiscoveries {
  type Identity[A] = A
}

object Id extends Monad[Identity] {
  override def pure[A](a: A): Identity[A] = a

  override def flatMap[A, B](fa: Identity[A])(f: (A) => Identity[B]): Identity[B] = {
    f(fa)
  }

  override def map[A, B](fa: Identity[A])(f: A => B): Identity[B] =
    f(fa)
}
