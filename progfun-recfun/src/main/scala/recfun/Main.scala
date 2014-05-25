package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if ((r> 0 && r<2) || c == 0 || c == r) 1 //first two rows contains only 1s, first&last column are also always 1
    else if (c > (r+1) || r<0 || c < 0) 0 //outside of the triangle
    else pascal(c, r-1) + pascal(c-1, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def recurse(chrlist: List[Char], open: Int): Int = {
      if (chrlist.isEmpty) open
      else if (open < 0) -1
      else if (chrlist.head == '(') recurse(chrlist.tail, open+1)
      else if (chrlist.head == ')') recurse(chrlist.tail, open-1)
      else recurse(chrlist.tail, open)
    }
    
    recurse(chars, 0) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    def findCombinationsCount(amount: Int, coinlist: List[Int], checkFromIndex: Int): Int = {
	    if (amount == 0) 1
	    else if (amount < 0 || coinlist.length == checkFromIndex) 0
	    else {
	      findCombinationsCount((amount-coinlist(checkFromIndex)), coinlist, checkFromIndex) + 
	      findCombinationsCount(amount, coinlist, checkFromIndex+1)
	    }
    }
    findCombinationsCount(money, coins, 0)
  }
}
