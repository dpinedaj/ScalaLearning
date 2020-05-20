object tuples {
    def main(args: Array[String]) = {
        /*a tuple in scala allows any datatype
        and are immutable elements*/
        val junkTup = (6, 3.14, "elephant", true)
        println(junkTup)
        //accessing index
        println(junkTup._2)

    }
}