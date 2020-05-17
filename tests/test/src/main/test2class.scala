class student(n: String, a: Int, r: Int) {
    var name :String = n
    var age :Int =a
    var rollno :Int =r

    def show()= {
        println("Name:"+name)
        println("Age:"+age)
        println("Rollno:"+rollno)
    }
}

object hello {
    def main(args: Array[String]) = {
        var s = new student("Daniel", 26, 1)
        s.show()
    }
}