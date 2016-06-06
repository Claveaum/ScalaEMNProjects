

/**
  * Created by mclaveau on 08/04/2016.
  */
trait Complex extends CartesianPolar with ComplexField {

}

object Complex {
    def apply(real: Double, img: Double) = new CarComplex(real, img)

    def apply(radius: Double, azimuth: Angle) = new PolComplex(radius, azimuth)

}