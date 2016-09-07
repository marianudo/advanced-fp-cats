package ch1

/**
  *
  */

import cats.Eq
import cats.syntax.eq._

object CatEq extends App {

    object cat {

        final case class Cat(name: String, age: Int, color: String)

        object Cat {
            implicit val catEqual = Eq.instance[Cat] {
                (cat1, cat2) => {
                    import cats.instances.int._
                    import cats.instances.string._
                    (cat1.name === cat2.name) &&
                        (cat1.age === cat2.age) &&
                        (cat1.color === cat2.color)
                }
            }
        }
    }

    import cat._

    val cat1 = Cat("Garfield", 35, "orange and black")
    val cat2 = Cat("Heathcliff", 30, "orange and black")
    val optionCat1: Option[Cat] = Some(cat1)
    val optionCat2: Option[Cat] = None
    println("cat1 === cat2 : " + (cat1 === cat2))
    println("cat1 =!= cat2 : " + (cat1 =!= cat2))

    // Bring Eq[Option] into scope for
    import cats.instances.option._
    import cats.syntax.eq._

    println("optionCat1 === optionCat2 : " + (optionCat1 === optionCat2))
    println("optionCat1 =!= optionCat2 : " + (optionCat1 =!= optionCat2))
}
