/**
  * Created by mclaveau on 08/04/2016.
  */
class PolComplex(val radius : Double, val azimuth : Angle) extends AbsComplex{
  val real: Double = Conversion.real(radius, azimuth)
  val img: Double = Conversion.img(radius, azimuth)

  def this(real: Double, img: Double) = this(Conversion.radius(real, img), Conversion.azimuth(real, img))

  def create(real: Double, imag: Double): Complex = {
     new PolComplex(real, imag)
  }

  def create(radius: Double, azimuth: Angle): Complex = {
     new PolComplex(radius, azimuth)
  }

}
