package ch06

import scala.language.higherKinds

object CartesianReview {
  trait Cartesian[F[_]] {
    def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  }

  object Cartesian {
    object Instances {
      implicit object OptionCartesian extends Cartesian[Option] {
        override def product[A, B](fa: Option[A], fb: Option[B]) = for {
          a <- fa
          b <- fb
        } yield (a, b)
      }
    }
  }
}
