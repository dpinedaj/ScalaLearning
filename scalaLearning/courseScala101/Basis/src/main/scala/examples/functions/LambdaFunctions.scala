package examples.functions

object LambdaFunctions {

  val numbers: List[Int] = List(4, 8, 15, 16, 23, 42)
  println(s"Initial numbers list: $numbers")

  var a = numbers.map[Int] { x => x * 2 }
  println(s"Numbers list after multiplying by 2: $a")

  var b = numbers.map[Double] { x => math.pow(x, 2) }
  println(s"Numbers list after pow 2: $b")

  val cars: List[String] = List("bmw", "mercedes", "seat", "renault")
  println(s"Initial cars list: $cars")

  val c = cars.map[String] { _.toUpperCase }
  println(s"Cars list after uppercase: $c")

  val cl = cars.map[Int] { _.length }
  println(s"Cars list word's length $cl")

  val numbersList: List[List[Int]] =
    List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
  val numbersList2 = numbersList.map[List[Int]] { x =>
    x.map[Int] { n => n * 2 }
  }
  println(numbersList2)

}
