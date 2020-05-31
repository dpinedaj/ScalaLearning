package examples.class_objects

class Automobile(
    var wheels: Int = 4,
    var engine: Int = 1,
    var lights: Int = 2
) {

  def total_parts():Int = {
    // No "self" needed, and implicit return
    wheels + engine + lights
  }

  def remove_wheels(count: Int):Unit = {
    if (wheels - count < 0) {
      throw new IllegalArgumentException(
        "Automobile cannot have fewer than 0 wheels!"
      )
    } else {
      wheels = wheels - count
      println(s"Auto now has $wheels wheels!")
    }
  }
}
