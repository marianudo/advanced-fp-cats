package ch01

import cats.Eq

import cats.instances.string._
import cats.instances.int._

import cats.syntax.eq._

object EqExampleForACat {
  implicit val catEq: Eq[Cat] = Eq.instance[Cat] {
    (c1, c2) => {
      c1.name === c2.name &&
      c1.age === c2.age &&
      c1.color === c2.color
    }
  }
}
