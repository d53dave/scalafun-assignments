package week2

object rationales {
  val x = new Rational(1,3)                       //> x  : week2.Rational = 1/3
  val y = new Rational(5,7)                       //> y  : week2.Rational = 5/7
  val z = new Rational(3,2)                       //> z  : week2.Rational = 3/2
  y.add(y)                                        //> res0: week2.Rational = 10/7
  x.add(x)                                        //> res1: week2.Rational = 2/3
  y.sub(z)                                        //> res2: week2.Rational = -11/14
  
  
  def sum(f: Int => Int)(a: Int, b: Int): Int =
   if(a>b) 0 else f(a) + sum(f)(a+1,b)            //> sum: (f: Int => Int)(a: Int, b: Int)Int
   
  sum(x => x*x)(1,3)                              //> res3: Int = 14
  val bound = 10                                  //> bound  : Int = 10
  val xs = for (i <- -bound to bound) yield i     //> xs  : scala.collection.immutable.IndexedSeq[Int] = Vector(-10, -9, -8, -7, -
                                                  //| 6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
}

class Rational(x: Int, y: Int){
  //println("Constructing with "+x+", "+y)
  require(y > 0, "denominator has to be greater than 0")
  
  def this(x: Int) = this(x,1)

	def numer = x
	def denum = y
	
	override def toString = {
	def g = gcd(x,y)
	numer/g + "/" +  denum/g
	}
	
	private def gcd(a: Int, b: Int): Int = if(b==0)a else gcd(b, a%b)
	
	def less(other: Rational) = numer * other.denum < other.numer * denum
	
	def max(other: Rational) = if(this.less(other)) other else this

	def add(other: Rational) =
	  new Rational(numer * other.denum + other.numer * denum, denum * other.denum)
	  
	  
	def sub(other: Rational) =
		this.add(other.neg)
	  
	def neg = new Rational(-numer, denum)
	}