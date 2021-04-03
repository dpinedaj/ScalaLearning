package examples.extra
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Futures2 extends App{

  val aFuture = Future({
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  })

  for (i <- 1 to 20) {
    println(i)
    Thread.sleep(100)
  }

}
