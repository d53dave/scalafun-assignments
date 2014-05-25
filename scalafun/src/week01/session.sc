package week01

object session {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  println("lol")                                  //> lol
  1+2                                             //> res0: Int(3) = 3
  def abs(x: Double) = if (x<0) -x else x         //> abs: (x: Double)Double
  abs(3)                                          //> res1: Double = 3.0
  abs(-3)                                         //> res2: Double = 3.0
  
  def sqrt(x: Double) = {
	  def sqrtIter(guess: Double): Double =
	  	if( isGoodEnough(guess) ) guess
	  	else sqrtIter(improve(guess))
	  	
	  def isGoodEnough(guess: Double) =
	  	abs(guess * guess - x)/x < 1e-5
	  	
	  def improve(guess: Double) =
	  	(guess + x / guess) / 2
	
		sqrtIter(1.0)     //return value
	}                                         //> sqrt: (x: Double)Double
	sqrt(2)                                   //> res3: Double = 1.4142156862745097
	
	
	
	
	 def max(xs: List[Int]): Int = {
     def maxhelper(xs2: List[Int]): Int = {
	    val max1 = xs2.head
	    val max2 = if (xs2.length >1) maxhelper(xs2.tail) else xs2.head
	    if (max1 > max2) max1 else max2
     }
     if (xs.isEmpty) throw new java.util.NoSuchElementException else maxhelper(xs)
  }                                               //> max: (xs: List[Int])Int
  
  max(List(-3,-2,-1))                             //> res4: Int = -1
}