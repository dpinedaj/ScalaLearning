package examples

object testApply {

    class Reverse {
        def apply(a: String):String = {
            a.reverse
        }
    }

    def main(args: Array[String]): Unit = {
        val rev = new Reverse
        println(rev("Hello"))
    }
}