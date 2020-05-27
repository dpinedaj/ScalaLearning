package examples

object CollectionsFunctions {
    val a = 1 to 5
    //map method allows to apply a function (arrow function or defined one) to all container's elements
    val aa = a.map(number => number + 1) //res0: scala.collection.immutable.IndexedSeq[Int] = Vector(2,3,4,5,6)
    val ab = a.map(_ + 1) // res0: the same of above

    //flatMap allows to apply the function and also decompose the intrinsic elements to merge all of them
    val b = List("Scala", "Python", "R")
    val ba = b.flatMap(lang => lang + "#") //res0: List[Char] = List(S, c, a, l, a, #, P, y, t, h, o, n, #, R, #)

    //filter allows to select just the elements that apply some condition
    val bb = b.filter(lang => lang.contains("S"))//res0: List[String] = List(Scala)

    //foreach apply a method to every element, but don't return a value
    val c = List(1, 2)
    c.foreach(print) //12

    //forall will look all the values to check if all apply some condition
    val d = List("Scala", "Simple", "Stellar")
    d.forall(lang => lang.contains("S"))//res0: Boolean = true
    d.forall(lang => lang.contains("a"))//res0: Boolean = false

    //reduce will make some operations into the insider's values together
    val e = 1 to 10
    val ea = e.reduce((acc, cur) => acc + cur)//res0: Int = 55
    val eb = e.reduce(_ + _) //res0: Int = 55

    //fold, foldLeft, foldRight
    val ec = e.foldLeft(0){ case (acc, cur) => acc + cur} //res0: Int = 55
    
    //product will multiply all the elements of a collection
    val ed = e.product //res0: Int = 3628800

    //exists will return true if the collection contains the specified element
    val ee = e.exists(x => x == 1) //res0: Boolean = true

    //find will return a Option if there's the specified element into the collection
    val ef = e.find(x => x == 1) //res0: Option[Int] = Some(1)

    //groupBy will group the collection and convert it into a Map
    val eg = e.groupBy(num => num % 2) //res0: scala.collection.immutable.Map[Int,IndexedSeq[Int]] 
                                      // = HashMap(0 -> Vector(2, 4, 6, 8, 10), 1 -> Vector(1, 3, 5, 7, 9))

    //takeWhile and dropWhile, will modify the collection that fits some condition
    val eh = e.takeWhile(num => num > 8)//res0: scala.collection.immutatble.Range = Range(9, 10)
    val ei = e.dropWhile(num => num < 8)//res0: scala.collection.immutatble.Range = Range(9, 10)

}