/**
  * Created by mclaveau on 08/04/2016.
  */
trait AbsComplex extends Complex {
  def +(that: Complex): Complex = {
    return this.create(real + that.real, img + that.img)
  }

  def *(that: Complex): Complex = {
    return this.create(radius * that.radius, azimuth + that.azimuth)
  }

  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[Complex])  false
    else {
    val c: Complex = that.asInstanceOf[Complex]
    Comparison.==(c.real, real) && (Comparison.==(c.img, img))
    }
  }

  override def toString: String = {
    return s"($real, $img)"
  }
}
