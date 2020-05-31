package examples.collections

import scala.collection.mutable
import scala.collection.immutable

object Sets {
  /*
    A set is a collection with no duplicate elements
    can be mutalbes or immutables
   */
  //Immutable set definition
  val set1 = Set(1, 5, 9, 5, 9, 10, 5)
  println(s"This is a immutable set: \n${set1}")

  //The default apply method of a set says if there's some element
  println(s"There is element: 5 on set1?: ${set1(5)}")

  //Casting a list to a set
  val listy = List(6, 8, 8, 12, 13, 13, 16)
  val sety = listy.toSet
  println(s"List casted to set ${sety}")

  val setBase: Set[Int] = Set(1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5)
  //Basic operations
  val setBase2 = setBase.map { x => x * 2 }
  println(s"Set after map $setBase2")
  var n = 2

  //For sets apply and contains are sinonimous
  println(s"Using the apply method on element: $n ${setBase.apply(n)}")
  println(s"Using the contains method on element: $n ${setBase.contains(n)}")
  println(s"Using the default method on element: $n ${setBase(n)}")
  println(s"This set is empty?: ${setBase.isEmpty}")

  //To concatenate sets, add or extract elements
  val set023 = setBase + 10
  val set123 = set023 ++ Set(1, 2, 3)
  val set223 = set123 - 1
  val set323 = set223 -- Set(4, 5)

  //some functions
  val set666 = Set(1, 2, 3)
  set666.diff(set123); set666 &~ set123
  set666.intersect(set123); set666 & set123
  set666.union(set123); set666 | set123

  //!Mutable set definition

  val mutableSet1 = mutable.Set(1, 5, 9)
  println(s"This is a mutable set: \n${mutableSet1}")

  //adding an element to mutable set
  n = 50
  mutableSet1 += n
  println(s"Mutable set after adding ${n}\n${mutableSet1}")

  n = 26
  mutableSet1.add(n)
  println(s"Mutable set after adding ${n}\n${mutableSet1}")

  mutableSet1 ++= Set(1, 12, 123)

  //retain operation
  mutableSet1.retain { x =>
    x % 2 == 0
  } //allow me to keep only the elements that pass some condition
  mutableSet1.filter(x => x % 2 == 0) //Is similar to retain

  //!Sorted Sets
  val sortedSet = immutable.SortedSet(1, 5, 6, 7, 2, 3, 4) // It sort me the elements of the set
  println(s"Sorted set: $sortedSet")
  val greatToLower = Ordering.fromLessThan[Int]({ (m, n) => m > n }); // Ordering.fromLessThan[Int](_> _)
  val inverseSortedSet = immutable.SortedSet.empty(greatToLower) ++ sortedSet
  println(s"The inversed sorted set is $inverseSortedSet")

}
