/**
  * Created by mclaveau on 19/04/2016.
  */
trait FP[T] {
  val selfCompose : (T => T, Int) => (T => T ) = (f, n) => x =>  n match {
    case 0 => x
    case e => selfCompose(f,n-1)(f(x))
  }
}
object FP extends FP[Double]