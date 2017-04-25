package ch03

import cats.Functor
import cats.syntax.functor._

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {

  implicit object TreeFunctor extends Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: (A) => B): Tree[B] = {
      fa match {
        case Leaf(a) => Leaf(f(a))
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      }
    }
  }

  implicit object LeafFunctor extends Functor[Leaf] {
    override def map[A, B](fa: Leaf[A])(f: (A) => B): Leaf[B] = Leaf(f(fa.value))
  }

  implicit object BranchFunctor extends Functor[Branch] {
    override def map[A, B](fa: Branch[A])(f: (A) => B): Branch[B] =
      Branch(
        fa.left.map(f),
        fa.right.map(f)
      )
  }
}
