/**
  * Created by mclaveau on 08/04/2016.
  */
trait Polar {
  def radius : Double
  def azimuth : Angle
  def *(that: Complex): Complex = {
    Complex(radius * that.radius, azimuth + that.azimuth)
  }
}

