import cats.data.State

type CalcState[A] = State[List[Int], A]
val program = for {
    _ <- evalOne("1")
    _ <- evalOne("2")
    ans <- evalOne("+")
} yield ans

evalOne("42").runA(Nil).value

def evalOne(sym: String): CalcState[Int] = {
    def operand(v: Int): CalcState[Int] = State[List[Int], Int] {
        stack => {
            (v :: stack, v)
        }
    }

    def operator(fx: (Int, Int) => Int): CalcState[Int] = {
        State[List[Int], Int] {
            state => {
                val (h1, t1) = (state.head, state.tail)
                val (h2, t2) = (t1.head, t1.tail)
                val fxApplyResult = fx(h1, h2)
                (fxApplyResult :: t2, fxApplyResult)
            }
        }
    }

    sym match {
        case "+" => operator(_ + _)
        case "-" => operator(_ - _)
        case "*" => operator(_ * _)
        case "/" => operator(_ / _)
        case n => operand(n.toInt)
    }
}

program.runA(Nil).value

def naiveEvalAll(input: List[String]): CalcState[Int] = {
    input match {
        case Nil => State[List[Int], Int] { state => (state, 0) }
        case h :: Nil => evalOne(h)
        case h :: t => evalOne(h).flatMap(_ => evalAll(t))
    }
}


import cats.syntax.applicative._

def evalAll(input: List[String]): CalcState[Int] = {
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
        a flatMap (_ => evalOne(b))
    }
}

val program2 = evalAll(List("1", "2", "+", "3", "*"))

program2.runA(Nil).value

val program3 = for {
    a <- evalAll(List("1", "2", "+"))
    b <- evalAll(List("3", "4", "+"))
    ans <- evalOne("*")
} yield {
    println(a); println(b); ans
}

program3.runA(Nil).value