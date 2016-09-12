package ch4

/**
  *
  */
object WriterMonadExercise extends App {

    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits.global

    import scala.concurrent.duration._

    import cats.data.Writer
    import cats.instances.vector._
    import cats.syntax.writer._
    import cats.syntax.applicative._

    type Logged[A] = Writer[Vector[String], A]

    def slowly[A](body: => A): A =
        try body finally Thread.sleep(100)

    def factorial(n: Int): Logged[Int] = {
        if(n == 0) 1.pure[Logged]
        else for {
            a <- slowly(factorial(n - 1))
            _ <- Vector(s"fact $n ${a * n}").tell
        } yield a * n
    }

    val result = Await.result(
        Future.sequence(
            Vector(Future(factorial(5).run), Future(factorial(5).run))
        ), Duration.Inf
    )

//    println(factorial(5).run)

    result.foreach(println)
}
