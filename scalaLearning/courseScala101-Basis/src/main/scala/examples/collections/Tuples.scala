package examples.collections


object Tuples {
    /*a tuple in scala allows any datatype
        and are immutable elements,
        can go up to 22 elements*/
    val junkTup = (6, 3.14, "elephant", true)
    //accessing index
    println(junkTup._2)//res0: Double = 3.14
    
    //Tuple2 definition
    val t2 = Tuple2(1, "a")//res0: (Int, String) = (1, "a")

    //To deconstruct a tuple
    val (e1, e2) = t2//res0: e1: Int = 1 e2: String = a

    //Tuple2 commonly called a pair
    //Also can be defined tuples as arrow indicator
    val t = (2->"b") //res0: (Int, String) = (2, "b")

    val t3 = (3 -> "c" -> 4) //res0: ((Int, String), Int) = ((3,c),4)

}
