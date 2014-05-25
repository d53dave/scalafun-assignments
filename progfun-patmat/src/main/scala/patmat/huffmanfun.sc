package patmat
import patmat.Huffman._

object huffmanfun {
	val sec = decodedSecret                   //> sec  : List[Char] = List(h, u, f, f, m, a, n, e, s, t, c, o, o, l)
	val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
                                                  //> t2  : patmat.Huffman.Fork = Fork(Fork(Leaf(a,2),Leaf(b,3),List(a, b),5),Leaf
                                                  //| (d,4),List(a, b, d),9)
	convert(frenchCode)                       //> res0: patmat.Huffman.CodeTable = List((s,List(0, 0, 0)), (d,List(0, 0, 1, 0)
                                                  //| ), (x,List(0, 0, 1, 1, 0, 0, 0)), (j,List(0, 0, 1, 1, 0, 0, 1)), (f,List(0, 
                                                  //| 0, 1, 1, 0, 1)), (z,List(0, 0, 1, 1, 1, 0, 0, 0, 0)), (k,List(0, 0, 1, 1, 1,
                                                  //|  0, 0, 0, 1, 0)), (w,List(0, 0, 1, 1, 1, 0, 0, 0, 1, 1)), (y,List(0, 0, 1, 1
                                                  //| , 1, 0, 0, 1)), (h,List(0, 0, 1, 1, 1, 0, 1)), (q,List(0, 0, 1, 1, 1, 1)), (
                                                  //| o,List(0, 1, 0, 0)), (l,List(0, 1, 0, 1)), (m,List(0, 1, 1, 0, 0)), (p,List(
                                                  //| 0, 1, 1, 0, 1)), (u,List(0, 1, 1, 1)), (r,List(1, 0, 0, 0)), (c,List(1, 0, 0
                                                  //| , 1, 0)), (v,List(1, 0, 0, 1, 1, 0)), (g,List(1, 0, 0, 1, 1, 1, 0)), (b,List
                                                  //| (1, 0, 0, 1, 1, 1, 1)), (n,List(1, 0, 1, 0)), (t,List(1, 0, 1, 1)), (e,List(
                                                  //| 1, 1, 0)), (i,List(1, 1, 1, 0)), (a,List(1, 1, 1, 1)))

}