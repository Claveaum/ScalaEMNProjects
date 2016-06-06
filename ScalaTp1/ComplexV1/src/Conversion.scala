/**
  * Created by mclaveau on 08/04/2016.
  */
import scala.math.{atan2, cos, sin, sqrt}

object Conversion {
  def real(radius : Double, azimuth : Angle) = radius * cos(azimuth.angle)
  def img(radius : Double, azimuth : Angle) = radius * sin(azimuth.angle)
  def radius(real: Double, imag: Double) = sqrt(real * real + imag * imag)
  def azimuth (real: Double, imag: Double) = new Angle(atan2(imag, real))
}
