object datatypes {
    def main(args: Array[String]) = {
        /*
        Scala is a language of statical typing, but it can autodetect
        the data types
        */
        //integer variable
        val a = 1  // val defines an inmmutable variable
        var aa: Int =1 // var defines a mutable variable
        println("This is a integer variable:", a)

        //Double or floating variable
        val b: Double = 1.5
        println("This is a double variable:", b)
        val bf = 1.5f
        println("This is a floating variable:", bf)
        //string variable
        val c: String = "hola"
        println("This is a string variable: " + c)

        //Scala arrays
        println("This is an Array")
        var d = Array(1, 2, 3)

        //The way to run through an array
        for (e <- d) println(e)

        for (e <- d) {
            val s = e + 10
            println(s)
        }

        //Booleans
        val f: Boolean = true
        println("This is a boolean: ", f)

        //null values
        val g: Null = null
        println("This is a Null: ", g)
    }
}