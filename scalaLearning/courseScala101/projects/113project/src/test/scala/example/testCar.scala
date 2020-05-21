package example

import example.Automobile

object carTest {
    def main(args: Array[String]): Unit = {
        val car = new Automobile()
        println(s"Car wheels: ${car.wheels}")
        println(s"Car engine: ${car.engine}")
        println(s"Car total parts: ${car.total_parts()}")
        println(s"${car.remove_wheels(2)}")
        println(s"Car wheels: ${car.wheels}")
        println(s"${car.remove_wheels(3)}")


    }
}