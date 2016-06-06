

/**
  * Created by mclaveau on 08/04/2016.
  */
trait Complex extends CartesianPolar with ComplexField {
  def create(real: Double, imag: Double): Complex

  def create(radius: Double, azimuth: Angle): Complex
}
