/**
  * Created by mclaveau on 08/04/2016.
  */
import scala.math.abs

object Comparison {
    val precision = 1E-15
    def ==(x : Double, y : Double) = abs(x - y) < precision

}
