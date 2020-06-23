import org.scalatest._

class Tests extends FunSuite with DiagrammedAssertions {
  test("Hello should start with H") {
    assert("hello".startsWith("h"))
  }
}