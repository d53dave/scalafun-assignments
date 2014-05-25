package week03

object listfun {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}

trait List[T] {
	def isEmpty: Boolean
	def head: T
	def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  def isEmpty = false
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
}

class Nil[T] extends List[T]{
  def isEmpty = true
  def head = throw new NoSuchElementException("Nil.head")
  def tail = throw new NoSuchElementException("Nil.tail")
}