object maps {
  //A map is similar to dictionary in python
  def main(args: Array[String]): Unit = {
    //! IMMUTABLE MAPS
    //map definition with tuples
    val animalsMap = Map(("aardvark", 1), ("bison", 2), ("cheeta", 3))
    println(animalsMap)

    //map with dependency
    val fruitsMap = ("apples" -> 4, "oranges" -> 5, "bananas" -> 6)
    println(fruitsMap)

    val animal = "aardvark"
    println(s"The value of ${animal} is ${animalsMap(animal)}")

    //! MUTABLE MAPS
    val animalsMutable =
      collection.mutable.Map(("elephant", 5), ("fox", 6), ("gecko", 7))

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

    for (i <- animalsMutable.keys){
        println(s"${i} -> ${animalsMutable(i)}")
    }

    
  }
}
