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

  def branch[A](l: Tree[A], r: Tree[A]): Tree[A] = Branch(l, r)

  def leaf[A](value: A): Tree[A] = Leaf(value)
}
