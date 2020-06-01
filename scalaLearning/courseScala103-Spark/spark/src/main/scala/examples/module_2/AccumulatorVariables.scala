package examples.module_2
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

//Module for Accumulator Variables
//The basic one
import org.apache.spark.Accumulator
//accumulator with utils library (The named one)
import org.apache.spark.util.LongAccumulator
//To explicit define the RDD type
import org.apache.spark.rdd.RDD

object AccumulatorVariables extends App {
  val sc = new SparkContext("local[*]", "Accumulator")

  val longAcc: LongAccumulator =
    sc.longAccumulator("SumAccumulator") // Can be named using the utils library

  val acc: Accumulator[Int] = sc.accumulator(0) //Can be unnamed using the basic accumulator

  val rdd: RDD[Int] = sc.parallelize(Array(1, 2, 3))

  rdd.foreach(x => longAcc.add(x))
  rdd.foreach(x => acc.add(x))
  println(s"longAcc: ${longAcc.value}") //Value vary from 3 to 6...
  println(s"Acc: ${acc.value}") //Value vary from 4 to 6...
}
