package ch03

import org.scalatest.FlatSpec

class PrintableContramapSpec extends FlatSpec {
  "A box containing a String" should "be printable" in {
    import PrintableInstances._

    val box: Box[String] = Box("Hello")

    assert(Printable.format(box) == "\"Hello\"")
  }
}
