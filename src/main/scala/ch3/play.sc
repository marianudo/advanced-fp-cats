// Mapping over a function
import cats.instances.function._
import cats.syntax.functor._

val f1 = (x: Int) => x.toDouble
val f2 = (d: Double) => d.toString

val f3 = f1 map f2

f3(45)

import scala.language.higherKinds

trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
}