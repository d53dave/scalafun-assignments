package objsets

object tweetplay {
		val set1 = new Empty              //> set1  : objsets.Empty = objsets.Empty@5ef4f44a
    val set2 = set1.incl(new Tweet("a", "a body", 20))
                                                  //> set2  : objsets.TweetSet = objsets.NonEmpty@76497934
    val set3 = set2.incl(new Tweet("b", "b body", 20))
                                                  //> set3  : objsets.TweetSet = objsets.NonEmpty@1f4384c2
    val c = new Tweet("c", "c body", 7)           //> c  : objsets.Tweet = User: c
                                                  //| Text: c body [7]
    val d = new Tweet("d", "d body", 9)           //> d  : objsets.Tweet = User: d
                                                  //| Text: d body [9]
    val set4c = set3.incl(c)                      //> set4c  : objsets.TweetSet = objsets.NonEmpty@9c0ec97
    val set4d = set3.incl(d)                      //> set4d  : objsets.TweetSet = objsets.NonEmpty@58ecb281
    val set5 = set4c.incl(d)                      //> set5  : objsets.TweetSet = objsets.NonEmpty@1bbb60c3
    
    val x1 = new Tweet("x1", "x1 body", 12)       //> x1  : objsets.Tweet = User: x1
                                                  //| Text: x1 body [12]
    val x2 = new Tweet("x2", "x2 body", 11)       //> x2  : objsets.Tweet = User: x2
                                                  //| Text: x2 body [11]
    val x3 = new Tweet("x3", "x3 body", 12)       //> x3  : objsets.Tweet = User: x3
                                                  //| Text: x3 body [12]
    val x4 = new Tweet("x4", "x4 body", 13)       //> x4  : objsets.Tweet = User: x4
                                                  //| Text: x4 body [13]
    val x6 = new Tweet("x6", "x6 body", 18)       //> x6  : objsets.Tweet = User: x6
                                                  //| Text: x6 body [18]
    val x7 = new Tweet("x7", "x7 body", 12)       //> x7  : objsets.Tweet = User: x7
                                                  //| Text: x7 body [12]
    val x8 = new Tweet("x8", "x8 body", 10)       //> x8  : objsets.Tweet = User: x8
                                                  //| Text: x8 body [10]
		val set6 : TweetSet = set5.incl(x1).incl(x2).incl(x3).incl(x4).incl(x6).incl(x7).incl(x8)
                                                  //> set6  : objsets.TweetSet = objsets.NonEmpty@2acdb06e
		
		val x5 = new Tweet("x5", "x5 body", 7)
                                                  //> x5  : objsets.Tweet = User: x5
                                                  //| Text: x5 body [7]
		val ret = set3.filter(tw => tw.user == "a" || tw.user == "b")
                                                  //> ret  : objsets.TweetSet = objsets.NonEmpty@47315d34
    ret foreach println                           //> User: a
                                                  //| Text: a body [20]
                                                  //| User: b
                                                  //| Text: b body [20]
    
    set6 foreach println                          //> User: a
                                                  //| Text: a body [20]
                                                  //| User: b
                                                  //| Text: b body [20]
                                                  //| User: c
                                                  //| Text: c body [7]
                                                  //| User: d
                                                  //| Text: d body [9]
                                                  //| User: x1
                                                  //| Text: x1 body [12]
                                                  //| User: x2
                                                  //| Text: x2 body [11]
                                                  //| User: x3
                                                  //| Text: x3 body [12]
                                                  //| User: x4
                                                  //| Text: x4 body [13]
                                                  //| User: x6
                                                  //| Text: x6 body [18]
                                                  //| User: x7
                                                  //| Text: x7 body [12]
                                                  //| User: x8
                                                  //| Text: x8 body [10]
    
    set6.filter(tw => tw.user == "x1" || tw.user == "x4" || tw.user == "x6" )  foreach println
                                                  //> User: x1
                                                  //| Text: x1 body [12]
                                                  //| User: x4
                                                  //| Text: x4 body [13]
                                                  //| User: x6
                                                  //| Text: x6 body [18]
    val reduced6 = set6.filter(tw => tw.user == "x1" || tw.user == "x4" || tw.user == "x6" )
                                                  //> reduced6  : objsets.TweetSet = objsets.NonEmpty@b6e39f
    reduced6.mostRetweeted                        //> res0: objsets.Tweet = User: x6
                                                  //| Text: x6 body [18]
    val desclist = set6.descendingByRetweet       //> User: a
                                                  //| Text: a body [20]
                                                  //| User: b
                                                  //| Text: b body [20]
                                                  //| User: x6
                                                  //| Text: x6 body [18]
                                                  //| User: x4
                                                  //| Text: x4 body [13]
                                                  //| User: x1
                                                  //| Text: x1 body [12]
                                                  //| User: x3
                                                  //| Text: x3 body [12]
                                                  //| User: x7
                                                  //| Text: x7 body [12]
                                                  //| User: x2
                                                  //| Text: x2 body [11]
                                                  //| User: x8
                                                  //| Text: x8 body [10]
                                                  //| User: d
                                                  //| Text: d body [9]
                                                  //| User: c
                                                  //| Text: c body [7]
                                                  //| desclist  : objsets.TweetList = objsets.Cons@266bade9
    
    def printList(tl: TweetList): Unit = {
    	println(tl.head)
    	printList(tl.tail)
    }                                             //> printList: (tl: objsets.TweetList)Unit
    
    printList(desclist)                           //> User: a
                                                  //| Text: a body [20]
                                                  //| User: b
                                                  //| Text: b body [20]
                                                  //| User: x6
                                                  //| Text: x6 body [18]
                                                  //| User: x4
                                                  //| Text: x4 body [13]
                                                  //| User: x1
                                                  //| Text: x1 body [12]
                                                  //| User: x3
                                                  //| Text: x3 body [12]
                                                  //| User: x7
                                                  //| Text: x7 body [12]
                                                  //| User: x2
                                                  //| Text: x2 body [11]
                                                  //| User: x8
                                                  //| Text: x8 body [10]
                                                  //| User: d
                                                  //| Text: d body [9]
                                                  //| User: c
                                                  //| Text: c body [7]
                                                  //| java.util.NoSuchElementException: head of EmptyList
                                                  //| 	at objsets.Nil$.head(TweetSet.scala:214)
                                                  //| 	at objsets.Nil$.head(TweetSet.scala:213)
                                                  //| 	at objsets.tweetplay$$anonfun$main$1.printList$1(objsets.tweetplay.scala
                                                  //| :34)
                                                  //| 	at objsets.tweetplay$$anonfun$main$1.apply$mcV$sp(objsets.tweetplay.scal
                                                  //| a:38)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide
                                                  //| Output exceeds cutoff limit.
    
    }