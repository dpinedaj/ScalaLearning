package examples

import examples.{Tuples, Lists, Maps, Sets, Arrays}

object TestCollections {
  def main(args: Array[String]): Unit = {
    println("\n########TUPLES########")
    val tuples = Tuples
    println("\n#######LISTS#######")
    val lists = Lists
    println("\n#######MAPS#######")
    val maps = Maps
    println("\n#######SETS#######")
    val sets = Sets
    println("\n#######ARRAYS#######")
    val arrays = Arrays
  }
}

object TestCollections2 {
  def main(args: Array[String]): Unit = {

    var map1 = Map(1->"one", 2->"two", 3->"three")
    map1 = map1 + ((3,"four"))
    println(map1)

    import collection.mutable
    val mutableMap1 = mutable.Map(1->"one", 2->"two", 3->"three")
    println(mutableMap1)
    mutableMap1(1) = "uno"
    println(mutableMap1)

  }
}