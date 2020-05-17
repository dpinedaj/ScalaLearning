object wileLoops{
    import util.control.Breaks.break

    def main(args: Array[String]) = {

        //using condition in loop
        println("\nwhile loop for x")
        var x = 0
        while(x < 5){
            println(s"x is currently $x; adding 1 to x")
            x += 1
        }

        //Using break statement
        println("\nwhile loop for y")
        var y = 0
        while(true){
            println(s"y is currently $y; adding 1 to y")
            y += 1
            if(y == 5) break
        }
    }
}