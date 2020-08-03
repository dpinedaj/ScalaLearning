package GeneralTests

import Lecture_2_1._
import Lecture_2_2._
import org.scalatest.FunSuite

class Tests extends FunSuite {
  test("HighOrderFunctions.usage") {
    assert(HighOrderFunctions.usage() canEqual Array(15, 255, 5))
  }
  test("AnonymousFunctions.usage") {
    assert(AnonymousFunctions.usage() == 50)
  }
  test("Currying.usage") {
    assert(Currying.usage() == 3091)
  }

  test("Exercise.usage") {
    assert(Exercise.usage() == 144)
  }
}