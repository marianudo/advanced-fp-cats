package monaderror

object Play {
  trait Numberizable[T] {
    def asInteger(t: T): Int
  }

  
}
