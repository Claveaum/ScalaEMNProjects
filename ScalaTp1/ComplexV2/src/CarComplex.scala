/**
  * Created by mclaveau on 08/04/2016.
  */
class CarComplex(val real: Double, val img: Double) {

  def ++(that: CarComplex): CarComplex = new CarComplex(real + that.real, img + that.img)



  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[CarComplex])  false
    else {
      val c: CarComplex = that.asInstanceOf[CarComplex]
      Comparison.==(c.real, real) && (Comparison.==(c.img, img))
    }
  }

  object CarComplex {

    implicit def pol2Car(pol : PolComplex) : CarComplex = new CarComplex(Conversion.real(pol.radius, pol.azimuth), Conversion.img(pol.radius, pol.azimuth))

  }

}

