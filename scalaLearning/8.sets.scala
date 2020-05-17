object sets {
    /*
    A set is a collection with no duplicate elements
    can be mutalbes or immutables
    */
    def main(args: Array[String]): Unit = {

        //Immutable set definition
        val set1 = Set(1, 5, 9, 5, 9, 10, 5)
        println(s"This is a immutable set: \n${set1}")

        //Mutable set definition
        val mutableSet1 = collection.mutable.Set(1, 5, 9)
        println(s"This is a mutable set: \n${mutableSet1}")

        //adding an element to mutable set
        var n = 50
        mutableSet1 += n
        println(s"Mutable set after adding ${n}\n${mutableSet1}")

        n = 26
        mutableSet1.add(n)
        println(s"Mutable set after adding ${n}\n${mutableSet1}")

        //Casting a list to a set
        val listy = List(6, 8, 8, 12, 13, 13, 16)
        val sety = listy.toSet
        println(s"List casted to set ${sety}")

    }
}