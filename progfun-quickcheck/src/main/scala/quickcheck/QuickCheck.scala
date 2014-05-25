package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }
  
  property("min2") = forAll { (a: Int, b: Int) => 
    val h = insert(b, insert(a,empty))
    findMin(h) == Math.min(a,b)
  }
  
  property("min3") = forAll { (a: Int, b: Int, c: Int) => 
    val h = insert(c, insert(b, insert(a,empty)))
    findMin(h) == List(a, b, c).min
  }
  
  property("min3") = forAll { (a: Int, b: Int, c: Int) => 
    val h = insert(c, insert(b, insert(a,empty)))
    val intList = List(a, b, c).sorted
    findMin(h) == intList(0) && findMin(deleteMin(h)) == intList(1) &&
    	findMin(deleteMin(deleteMin(h))) == intList(2)
  }
  
  property("add2 delete2 should be empty") = forAll{ a: Int =>
    val h = deleteMin(insert(a, empty))
    h == empty
  }
  
  property("gen1") = forAll { (h: H) =>
  val m = if (isEmpty(h)) 0 else findMin(h)
  findMin(insert(m, h))==m
}
  
  property("minimum of meld") = forAll{ (a: Int, b: Int) =>
    val h = meld( insert(a, empty), insert(b, empty))
    findMin(h) == Math.min(a,b)
  }
  
  property("order of items") = forAll{ (a: Int, b: Int) =>
    val h = insert(b, insert(a, empty))
    val (sm: Int, bi:Int) = if(a<b) (a, b) else (b,a)
    findMin(h) == sm && findMin(deleteMin(h)) == bi
  }
  

  lazy val genHeap: Gen[H] = for {
    i1 <- arbitrary[Int]
    i2 <- arbitrary[Int]
    h <- oneOf(value(empty), genHeap)
  } yield insert(i1, insert(i2, h))

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
