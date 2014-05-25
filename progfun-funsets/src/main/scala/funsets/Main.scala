package funsets

object Main extends App {
  import FunSets._
  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)
  //println(contains(s1, 1))
  
  
  def greaterThan3(n: Int) = n>3
  def smallerThan3(n: Int) = n<3
  def smallerThan1(n: Int) = n<1
  def greaterThan1(n: Int) = n>1
  def emptySet(n: Int) = false
    
    
  def s:Set = diff(smallerThan3, greaterThan1)
  //printSet(s)
  
  val d:Set = filter(greaterThan1, x => x%2==0 && x<20)
  printSet(d)
  
  val d1:Set = filter(greaterThan1, x => x%2==0)
  printSet(d1)
  
  val u = union(s1, s2)
  println("All in "+ funsets.FunSets.toString(u) +" even? "+forall(u, (x: Int) => x % 2 == 0))
  println("Any in "+ funsets.FunSets.toString(u) +" even? "+exists(u, (x: Int) => x % 2 == 0))
  
  val s4 = union(union(s1, s2), s3)      
  printSet(s4)  // {1, 2, 3}      
  val t = map(s4, (x: Int) => x*x)      
  printSet(t)  // {1, 4, 9}      
  //assert(contains(t, 9))
  
  
}