package ch4

/**
  *
  */
object WriterMonadExercise extends App {

    import scala.concurrent._
    import scala.concurrent.ExecutionContext.Implicits.global

    import scala.concurrent.duration._

    def slowly[A](body: => A) =
        try body finally Thread.sleep(100)

    def factorial(n: Int): Int = {
        val ans = slowly(if (n == 0) 1 else n * factorial(n - 1))
        println(s"fact $n $ans")
        ans
    }

    Await.result(
        Future.sequence(
            Vector(Future(factorial(5)), Future(factorial(5)))
        ), Duration.Inf
    )
}
