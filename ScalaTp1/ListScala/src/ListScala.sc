val liste = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: Nil

def myLength[T]: List[T] => Int = {
  case Nil => 0
  case _ :: t => 1 + myLength(t)
}


val resLength = myLength(liste)

def myReverse[T]: List[T] => List[T] = {
  case ( x :: xs ) => myReverse(xs) ::: List(x)
  case xs => xs
}

val resReverse = myReverse(liste)

def myReverse1[T]: List[T] =>  List[T] = {
  def myReverse2[T](l1 : List[T], l2 : List[T]) : List[T] = l1 match {
    case Nil => l2
    case x :: xs => myReverse2(xs, x::l2)
  }
  myReverse2(_, Nil)
}

val resReverse2 = myReverse(liste)

import FP._
val res = selfCompose(math.cos,100)(1000)

