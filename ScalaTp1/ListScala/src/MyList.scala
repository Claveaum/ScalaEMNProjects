/**
  * Created by mclaveau on 19/04/2016.
  */
trait MyList[T] {

  val myLength: List[T] => Int = {
    case Nil => 0
    case _ :: t => 1 + myLength(t)
  }


  val myReverse: List[T] => List[T] = {
    case ( x :: xs ) => myReverse(xs) ::: List(x)
    case xs => xs
  }


  val myReverse1: List[T] =>  List[T] = {
    def myReverse2(l1 : List[T], l2 : List[T]) : List[T] = l1 match {
      case Nil => l2
      case x :: xs => myReverse2(xs, x::l2)
    }
    myReverse2(_, Nil)
  }

  def myReverse1F: List[T] =>  List[T] = {
    def myReverse2(l1 : List[T], l2 : List[T]) : List[T] = l1 match {
      case Nil => l2
      case x :: xs => myReverse2(xs, x::l2)
    }
    myReverse2(_, Nil)
  }

}
