package forcomp

object ana {

type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  type Occurrences = List[(Char, Int)]
	val word:String = "David"                 //> word  : String = David
	val ca = word.toLowerCase.toCharArray.sorted
                                                  //> ca  : Array[Char] = Array(a, d, d, i, v)
	
	ca.groupBy((element: Char) => element).toList
                                                  //> res0: List[(Char, Array[Char])] = List((v,Array(v)), (d,Array(d, d)), (a,Arr
                                                  //| ay(a)), (i,Array(i)))

def occ(word: String) = word.toLowerCase.groupBy(_.toChar).map(p => (p._1, p._2.length))
                                                  //> occ: (word: String)scala.collection.immutable.Map[Char,Int]
val a = occ("David").toList                       //> a  : List[(Char, Int)] = List((v,1), (d,2), (a,1), (i,1))
val b = "v".groupBy(_.toChar).map(p => (p._1, p._2.length)).toList
                                                  //> b  : List[(Char, Int)] = List((v,1))
val c = occ("assess").toList.sorted               //> c  : List[(Char, Int)] = List((a,1), (e,1), (s,4))

val l = List("Hello", "World", "ooo")             //> l  : List[String] = List(Hello, World, ooo)
l mkString                                        //> res1: String = HelloWorldooo

def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    (for((x_chr, x_cnt) <- x; (y_chr, y_cnt) <- y) yield if (x_chr == y_chr) (x_chr, x_cnt-y_cnt) else (x_chr, x_cnt)).sorted filter ( x => x._2 != 0)
}                                                 //> subtract: (x: forcomp.ana.Occurrences, y: forcomp.ana.Occurrences)forcomp.an
                                                  //| a.Occurrences
                                                  
subtract(a, b)                                    //> res2: forcomp.ana.Occurrences = List((a,1), (d,2), (i,1))

	List("Hello", "there").map(_*2)           //> res3: List[String] = List(HelloHello, therethere)
}