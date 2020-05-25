package examples
import scala.collection.mutable

object Maps {
  //A map is similar to dictionary in python
  //! IMMUTABLE MAPS
  //map definition with tuples
  val animalsMap = Map(("aardvark", 1), ("bison", 2), ("cheeta", 3))
  println(animalsMap)

  //map with dependency
  val fruitsMap = Map(1 -> "apples", 2 -> "oranges", 3 -> "bananas")
  println(fruitsMap)

  val animal = "aardvark"
  println(s"The value of ${animal} is ${animalsMap(animal)}")

  //To check the keys:
  println(s"The keys are: ${fruitsMap.keySet}")

  //To check the values
  println(s"The values are: ${fruitsMap.values}")

  //To check a specific element
  println(
    s"The element 2 of fruitsMap is ${fruitsMap.get(2)}"
  ) //The method get allows to check if exists, also returns a None if

  println(
    s"The element 3 of fruitsMap is ${fruitsMap(3)}"
  ) //with this way it can take an error if doesnot exists

  println(
    s"The element 5 of fruitsMap is ${fruitsMap.getOrElse(5, "Doesn't Exists")}"
  ) //with this method it returns a default value if doesn't exists

  //To add new elements
  var otherMap = fruitsMap + (10 -> "pinneaple")
  //To subtract elements
  otherMap = otherMap - 3 //It will subtract the element of key 3 of the Map

  val mapFruitsAnimals = animalsMap ++ fruitsMap
  println(s"The mix of fruits and Animals is $mapFruitsAnimals")

  //! MUTABLE MAPS
  val animalsMutable =
    mutable.Map(("elephant", 5), ("fox", 6), ("gecko", 7))

  println(animalsMutable)

  val valueAnimal = ("hippo" -> 8)
  println(s"Adding ${valueAnimal}")
  animalsMutable += valueAnimal
  println(s"Map after the add ${animalsMutable}")

  println(
    "The keys of the map are " +
      s"${animalsMutable.keys}"
  )
  println(
    "The values of the map are " +
      s"${animalsMutable.values}"
  )

  for (i <- animalsMutable.keys) {
    println(s"${i} -> ${animalsMutable(i)}")
  }

  //In a mutable map i can change a value accessing by his key
  val numbersMap = mutable.Map(1 -> '1', 2 -> '2', 3 -> '3')
  println(s"The first numbersmap is $numbersMap")
  numbersMap(1) = '5'
  numbersMap(2) = '0'
  numbersMap(3) = '9'
  println(s"The second numbersmap is $numbersMap")

  //to subtract a element
  numbersMap -= 1 //Deletes the element with key 1

  //!Creating a Map from group a list
  val listy = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val mapyL = listy.groupBy[Int](x => x % 3)
  println(s"The initial list is: $listy\nThe transformed is: $mapyL")
}
