package ch03

import org.scalatest.FlatSpec

import PrintableInstances._

class PrintableContramapSpec extends FlatSpec {
  "A box containing a String" should "be printable" in {
    val box: Box[String] = Box("Hello")

    assert(Printable.format(box) == "\"Hello\"")
  }

  "A box containing a true Boolean" should "be printable" in {
    val box: Box[Boolean] = Box(true)

    assert(Printable.format(box) == "yes")
  }
}
