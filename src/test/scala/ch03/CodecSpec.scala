package ch03

import org.scalatest.FlatSpec

class CodecSpec extends FlatSpec {
  "A Box[Int]" should "can be coded" in {
    import Codec._
    
    val box: Box[Int] = Box(100)
    assert(encode(box) == "100")
  }

  "An Int" should "be codec into a box[Int]" in {
    import Codec.{boxCodec, decode}

    val integer = "140"
    assert(decode("140").contains(Box(140)))
  }
}
