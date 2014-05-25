package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    def greaterThan3(n: Int) = n>3
    def smallerThan3(n: Int) = n<3
    def smallerThan1(n: Int) = n<1
    def greaterThan1(n: Int) = n>1
    def evenSet(n: Int) = n%2 == 0
    def oddSet(n: Int) = n%2 != 0
    def lowerboundSet(n: Int) = n == -bound
    def upperboundSet(n: Int) = n == bound
    def emptySet(n: Int) = false
    def fullSet(n: Int) = true
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 and nothing else") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
    }
  }
  
  test("Union of n>3 and n<1"){
    new TestSets{
      val s = union(greaterThan3, smallerThan1)
      assert(!contains(s, 2), "shouldn't contain 2")
      assert(contains(s, -4), "should contain -4")
      assert(contains(s, 99), "should contain 99")
    }
  }

  test("Intersection of n>3 and n>1") {
    new TestSets {
      val s = intersect(greaterThan3, greaterThan1)
      assert(!contains(s, 2), "shouldnt contain 2")
      assert(!contains(s, 3), "shouldnt contain 3")
      assert(contains(s, 4), "should contain 4")
    }
  }
  
  test("Diff of n<3 and n>1") {
    new TestSets {
      val s = diff(smallerThan3, greaterThan1)
      assert(contains(s, 0), "should contain 0")
      assert(contains(s, -10), "should contain -10")
      assert(contains(s, -899879810), "should contain -899879810")
      assert(!contains(s, 899879810), "shouldnt contain 899879810")
      assert(!contains(s, 2), "shouldnt contain 2")
      assert(!contains(s, 4), "shouldnt contain 4")
    }
  }
  
  test("Filter of n<3 and n>1") {
    new TestSets {
      val s = filter(greaterThan3, greaterThan1)
      assert(!contains(s, 2), "shouldnt contain 2")
      assert(!contains(s, 3), "shouldnt contain 3")
      assert(contains(s, 4), "should contain 4")
    }
  }
  
  test("forall in empty set") {
    new TestSets {
      assert(forall(emptySet, x => x%2 == 0), "all should eval to true")
      assert(forall(emptySet, x => x%2 != 0), "all should eval to true")
    }
  }
  
  test("forall even and smaller than 20") {
    new TestSets {
      val s = filter(evenSet, (x:Int) => x < 20)
      assert(forall(s, x => x%2 == 0), "all should be even")
      assert(!forall(s, x =>  x > 10), "condition x>10 should result false, since set is all even from -1000 to 18")
      assert(!forall(s, x =>  x > 20), "condition x>20 should result false in all circumstances")
      assert(forall(s, x => x < 20), "all should be smaller than 20")
    }
  }
  
  test("exists even and smaller than 20") {
    new TestSets {
      val s = filter(evenSet, (x:Int) => x < 20)
      assert(exists(s, x => x%2 == 0), "should contain some even number")
      assert(!exists(s, x => x>20), "x>20 should not exist")
      assert(exists(s, x => x<20), "x<20 should exist (except in the case of an empty set")
      assert(!exists(s, x => x%2 != 0), "odd numbers should not exist")
    }
  }
  
  test("map square function to {1,2,3}") {
    new TestSets {
      val s = union(union(s1, s2), s3)    
      val t = map(s, (x: Int) => x*x)  
      assert(funsets.FunSets.toString(t) == "{1,4,9}")
    }
  }
}
