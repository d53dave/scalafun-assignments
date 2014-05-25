package week2

object sets {
  type Set = Int => Boolean
  
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: Int => Boolean, elem: Int)Boolean
  val bound = 1000                                //> bound  : Int = 1000
  
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if ( contains(s,a) && !p(a) ) false
      else if (a < -bound || a > bound ) true
      else iter(a+1)
    }
    iter(-bound)
  }                                               //> forall: (s: Int => Boolean, p: Int => Boolean)Boolean
  
  def exists(s: Set, p: Int => Boolean):
  	Boolean = !forall(s, !p(_))               //> exists: (s: Int => Boolean, p: Int => Boolean)Boolean
  	
  def union(s: Set, t: Set): Set = { n: Int => s(n) || t(n) }
                                                  //> union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean
  def singletonSet(elem: Int): Set = { n:Int => elem == n }
                                                  //> singletonSet: (elem: Int)Int => Boolean
  	
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }                                               //> toString: (s: Int => Boolean)String
  
  def evenSet(n: Int) = n%2 == 0                  //> evenSet: (n: Int)Boolean
  println(toString(evenSet))                      //> {-1000,-998,-996,-994,-992,-990,-988,-986,-984,-982,-980,-978,-976,-974,-972
                                                  //| ,-970,-968,-966,-964,-962,-960,-958,-956,-954,-952,-950,-948,-946,-944,-942,
                                                  //| -940,-938,-936,-934,-932,-930,-928,-926,-924,-922,-920,-918,-916,-914,-912,-
                                                  //| 910,-908,-906,-904,-902,-900,-898,-896,-894,-892,-890,-888,-886,-884,-882,-8
                                                  //| 80,-878,-876,-874,-872,-870,-868,-866,-864,-862,-860,-858,-856,-854,-852,-85
                                                  //| 0,-848,-846,-844,-842,-840,-838,-836,-834,-832,-830,-828,-826,-824,-822,-820
                                                  //| ,-818,-816,-814,-812,-810,-808,-806,-804,-802,-800,-798,-796,-794,-792,-790,
                                                  //| -788,-786,-784,-782,-780,-778,-776,-774,-772,-770,-768,-766,-764,-762,-760,-
                                                  //| 758,-756,-754,-752,-750,-748,-746,-744,-742,-740,-738,-736,-734,-732,-730,-7
                                                  //| 28,-726,-724,-722,-720,-718,-716,-714,-712,-710,-708,-706,-704,-702,-700,-69
                                                  //| 8,-696,-694,-692,-690,-688,-686,-684,-682,-680,-678,-676,-674,-672,-670,-668
                                                  //| ,-666,-664,-662,-660,-658,-656,-654,-652,-650,-648,-646,-644,-642,-640,-638,
                                                  //| -636,-634,-632,-630,-628
                                                  //| Output exceeds cutoff limit.
  
  val u = union(singletonSet(1), singletonSet(2)) //> u  : Int => Boolean = <function1>
  println("All in "+ toString(u) +" >0? "
  	+forall(u, (x: Int) => x > 0))            //> All in {1,2} >0? true
  println("All in "+ toString(u) +" even? "
  	+forall(u, (x: Int) => x % 2 == 0))       //> All in {1,2} even? false
  println("Any in "+ toString(u) +" even? "
  	+exists(u, (x: Int) => x % 2 == 0))       //> Any in {1,2} even? true
}