package utils

import scala.concurrent._, duration._
import scrappy.Weather

object UseFromScrappy extends App {
  val w = Await.result(Weather.weather, 10.seconds)
  println(s"Hello! The weather in New York is $w.")
  Weather.http.close()
}