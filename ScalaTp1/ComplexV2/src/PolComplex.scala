/**
  * Created by mclaveau on 08/04/2016.
  */
class PolComplex(val radius : Double, val azimuth : Angle){

  def *(c: PolComplex) = new PolComplex(radius * c.radius, azimuth + c.azimuth)

  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[PolComplex])  false
    else {
      val c: PolComplex = that.asInstanceOf[PolComplex]
      Comparison.==(c.radius, radius) && (Comparison.==(c.azimuth.angle, azimuth.angle))
    }
  }

  object PolComplex {

    implicit def car2Pol(car : CarComplex) : PolComplex = new PolComplex(Conversion.radius(car.real, car.img), Conversion.azimuth(car.real, car.img))

  }

}
