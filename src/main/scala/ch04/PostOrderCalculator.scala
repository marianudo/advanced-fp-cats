package ch04

object PostOrderCalculator {

  import cats.data.State

  type CalcState[A] = State[List[Int], A]

  def operand(i: Int): CalcState[Int] = State {
    list => (i :: list, i)
  }

  def operator(fx: (Int, Int) => Int): CalcState[Int] = State {
    case h1 :: h2 :: t =>
      val fxResult = fx(h1, h2)
      (fxResult :: t, fxResult)

    case _ => throw new IllegalStateException
  }


  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+" => operator(_ + _)
      case "-" => operator(_ - _)
      case "*" => operator(_ * _)
      case "/" => operator(_ / _)
      case num => operand(num.toInt)
    }

  import cats.syntax.applicative._

  def evalAll(input: List[String]): CalcState[Int] = {

    input.foldLeft(0.pure[CalcState]) {(a, b) =>
      a flatMap (_ => evalOne(b))
    }
  }
}
