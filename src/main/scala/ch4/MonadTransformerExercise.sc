type Error = String

import cats.data.XorT

import scala.concurrent.Future

import cats.implicits._

type FutureXor[A] = XorT[Future, Error, A]

val loadAverages =
    Map( "a.example.com" -> 0.1, "b.example.com" -> 0.5, "c.example.com" -> 0.2)

def getLoad(hostname: String): FutureXor[Double] = {
//    import cats.syntax.applicative._
//    import cats.instances.future._
//
//    import cats.instances.string._

    val hostNameMonad = hostname.pure[FutureXor]
    ???
}

println(getLoad("pepe"))