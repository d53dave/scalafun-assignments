package week03

object treefun {
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : week03.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : week03.IntSet = {.3{.4.}}
  val t3 = t2 union t2 union t2                   //> t3  : week03.IntSet = {.3{.4.}}
  
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
                                                  //> singleton: [T](elem: T)week03.Cons[T]
  def lookup[T](cons: List[T], idx: Int): T = {
  	def recurse(cs: List[T], index: Int): T = {
  		if (cs.isEmpty) throw new IndexOutOfBoundsException
  		else if(index == idx) cs.head
  		else recurse(cs.tail, index+1)
  		
  	}
  	recurse(cons, 0)
  }                                               //> lookup: [T](cons: week03.List[T], idx: Int)T
  
  val l1 = singleton(1)                           //> l1  : week03.Cons[Int] = week03.Cons@1f4384c2
  val l2 = new Cons(2, l1)                        //> l2  : week03.Cons[Int] = week03.Cons@9c0ec97
  val l3 = new Cons(3, l2)                        //> l3  : week03.Cons[Int] = week03.Cons@58ecb281
  val l4 = new Cons(4, l3)                        //> l4  : week03.Cons[Int] = week03.Cons@1bbb60c3
  lookup(l4, 2)                                   //> res0: Int = 2
}

abstract class IntSet{
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(other: IntSet): IntSet
}

object Empty extends IntSet{
	def contains(x: Int): Boolean = false
	def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet) = other
  override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet{
	def contains(x: Int): Boolean =
		if (x<elem) left contains x
		else if(x>elem) right contains x
		else true //found!
		
	def incl(x: Int): IntSet=
		if(x<elem) new NonEmpty(elem, left incl x, right)
		else if (x>elem) new NonEmpty(elem, left, right incl x)
		else this
		
	def union(other: IntSet): IntSet =
	((left union right) union other) incl elem
	override def toString = "{" + left + elem + right + "}"
}