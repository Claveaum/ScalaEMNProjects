/**
  * Created by mclaveau on 08/04/2016.
  */
trait Cartesian {
  def real : Double
  def img : Double
  def +(that: Complex): Complex = {
    Complex(real + that.real, img + that.img)
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
