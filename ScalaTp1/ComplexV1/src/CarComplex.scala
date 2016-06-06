/**
  * Created by mclaveau on 08/04/2016.
  */
class CarComplex(val real: Double, val img: Double) extends AbsComplex {

  val radius = Conversion.radius(real, img)

  val azimuth = Conversion.azimuth(real, img)

  def this(radius: Double, azimuth: Angle) = this(Conversion.real(radius, azimuth), Conversion.img(radius,azimuth))

  def create(real: Double, imag: Double): Complex = {
    new CarComplex(real, imag)
  }

  def create(radius: Double, azimuth: Angle): Complex = {
    new CarComplex(radius, azimuth)
  }
}

