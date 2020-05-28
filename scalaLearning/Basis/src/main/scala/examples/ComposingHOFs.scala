package examples

object ComposingHOFs {
    val myNums = 1 to 3
    val a = myNums.map(i => (1 to i).map(j => i * j)) //res0: Vector(Vector(1), Vector(2, 4), Vector(3, 6, 9))
    val b = myNums.flatMap(i => (1 to i).map(j => i * j)) //res1: Vector(1, 2, 4, 3, 6, 9)
    
}