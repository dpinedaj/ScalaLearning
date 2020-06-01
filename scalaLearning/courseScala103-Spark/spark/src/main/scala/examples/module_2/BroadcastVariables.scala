package examples.module_2

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

//Module for Broadcasting Variables
import org.apache.spark.broadcast.Broadcast

//own modules
import utils._

object BroadcastVariables extends App {

  /*This will create 2 broadcasted maps that can be queried by every cluster
    without the need of transfer the data through the network */

  val sc = new SparkContext("local[*]", "Broadcast")
  val outpath :String = "data/broadcasting"
  MyFiles.clearDirectory(outpath)
  val states = Map(("NY", "New York"), ("CA", "California"), ("FL", "Florida"))
  val countries = Map(("USA", "United States of America"), ("IN", "India"))

  val broadcastStates
      : Broadcast[Map[String, String]] = sc.broadcast(states) //can pass the variable
  val broadcastCountries = sc.broadcast(countries) //or can be infered

  val data = Seq(
    ("James", "Smith", "USA", "CA"),
    ("Michael", "Rose", "USA", "NY"),
    ("Robert", "Williams", "USA", "CA"),
    ("Maria", "Jones", "USA", "FL")
  )

  //!With rdds example
  val rdd = sc.parallelize(data)

  val rdd2 = rdd.map(f => {
    val country = f._3
    val state = f._4
    val fullCountry = broadcastCountries.value.getOrElse(country, "")
    val fullState = broadcastStates.value.getOrElse(state, "")
    (f._1, f._2, fullCountry, fullState)
  })
  println(rdd2.getNumPartitions)
  println(
    rdd2.collect().mkString("\n")
  ) //The mkString method generates a string, separating by "\n"(In this case)

  rdd2.coalesce(1)
  rdd2.saveAsTextFile(outpath + "rdd")
  //!With Dataframes

  val spark = SparkSession
    .builder()
    .appName("Broadcast")
    .master("local")
    .getOrCreate()

  val columns = Seq("firstname", "lastname", "country", "state")

  import spark.sqlContext.implicits._

  val df = data.toDF(columns: _*)

  val df2 = df
    .map(row => {
      val country = row.getString(2)
      val state = row.getString(3)

      val fullCountry = broadcastCountries.value.get(country).get
      val fullState = broadcastStates.value.get(state).get
      (row.getString(0), row.getString(1), fullCountry, fullState)
    })
    .toDF(columns: _*)

  df2.show(false)

}
