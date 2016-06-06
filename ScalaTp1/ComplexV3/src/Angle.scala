/**
  * Created by mclaveau on 08/04/2016.
  */
import scala.math.{Pi, floor}

class Angle(radians : Double) {
  val angle = radians - 2 * Pi * floor(radians / (2 * Pi ))

  def +(that : Angle) = new Angle(angle + that.angle)

  def *(k : Double) = new Angle(angle * k)

}
