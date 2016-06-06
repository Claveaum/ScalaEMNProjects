trait X {
  val x = 0
  override def toString = " x: " + x
}
trait Y {
  val y = 0
  override def toString = " y: " + y
}

class XY extends X with Y{}

val a = new XY

println(a.toString)

List(1) :: List(1.0) :: Nil

class EMail(name: String, domain: String)

object EMail {
  def apply(name: String, domain: String) = new EMail(name, domain)
}

val jn = EMail("noye", "emn.fr")

