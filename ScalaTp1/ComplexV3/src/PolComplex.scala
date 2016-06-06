/**
  * Created by mclaveau on 08/04/2016.
  */
class PolComplex(val radius : Double, val azimuth : Angle) extends  Complex{
  val real: Double = Conversion.real(radius, azimuth)
  val img: Double = Conversion.img(radius, azimuth)

  def this(real: Double, img: Double) = this(Conversion.radius(real, img), Conversion.azimuth(real, img))

  def create(real: Double, imag: Double): Complex = {
     new PolComplex(real, imag)
  }

  def create(radius: Double, azimuth: Angle): Complex = {
     new PolComplex(radius, azimuth)
  }

  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[Complex])  false
    else {
      val c: Complex = that.asInstanceOf[Complex]
      Comparison.==(c.radius, radius) && (Comparison.==(c.azimuth.angle, azimuth.angle))
    }
  }

  override def toString: String = {
    return s"($radius e^ i$azimuth)"
  }

}
