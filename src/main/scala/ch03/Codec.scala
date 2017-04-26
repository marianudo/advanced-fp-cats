package ch03

import scala.util.Try

trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    val self = this

    new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))

      override def decode(value: String): Option[B] = self.decode(value).map(dec)
    }
  }
}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)

  implicit object IntCodec extends Codec[Int] {
    override def encode(value: Int): String = value.toString

    override def decode(value: String): Option[Int] = Try(value.toInt).toOption
  }

  implicit def boxCodec[A](implicit codec: Codec[A]): Codec[Box[A]] =
    codec.imap(
      Box(_),
      _.value
    )
}
