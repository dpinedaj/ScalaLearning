package examples.module_1

import org.apache.spark.SparkContext

//To set desired logs
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.rdd.RDD

import org.apache.spark.mllib.linalg.distributed.IndexedRow

object SparkRandomSplit {
  Logger.getLogger("org").setLevel(Level.OFF)
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "Statistics")

    try {
      simpleSampling(sc)
      randomSplit(sc)
      stratifiedSampling(sc)
    } finally {
      sc.stop()
    }
  }
  def simpleSampling(sc: SparkContext): Unit = {
      println("\nSimple Sampling")
    def takeSample(
        elements: RDD[Vector],
        withReplacement: Boolean,
        fraction: Double,
        seed: Long
    ): Array[Vector] = {
      val result = elements
        .sample(
          withReplacement = withReplacement,
          fraction = fraction,
          seed = seed
        )
        .collect()
      return result

    }
    val elements: RDD[Vector] = sc.parallelize(
      Array(
        Vectors.dense(4.0, 7.0, 13.0),
        Vectors.dense(-2.0, 8.0, 4.0),
        Vectors.dense(3.0, -11.0, 19.0)
      )
    )
    println("No replacement, 0.5 with seed 10L")
    takeSample(elements, false, 0.5, 10L).foreach(println)
    println("No replacement, 0.5 with seed 7L")
    takeSample(elements, false, 0.5, 7L).foreach(println)
    println("No replacement, 0.5 with seed 64L")
    takeSample(elements, false, 0.5, 64L).foreach(println)

  }
  def randomSplit(sc: SparkContext): Unit = {
    println("\nrandomSplit Sampling")
    def takeRandom[T](
        data: RDD[T],
        split: Array[Double],
        seed: Long
    ): Array[RDD[T]] = {
      val splits = data.randomSplit(split, seed = seed)
      return splits
    }

    val data = sc.parallelize(1 to 20)
    val splits = takeRandom(data, Array(0.5, 0.2, 0.3), seed = 13L)
    println("training:\n", splits(0).collect().mkString(", "))
    println("tests:\n", splits(1).collect().mkString(", "))
    println("validation:\n", splits(2).collect().mkString(", "))
    println("counts:\n", splits.map(_.count()).mkString(", ") )
  }

  def stratifiedSampling(sc: SparkContext): Unit = {
    println("\nStratified Sampling:")
    def takeStratSample[T](
        rows: RDD[T],
        fractions: Map[Long, Double],
        withReplacement: Boolean,
        seed: Long
    ): RDD[(Long, Vector)] = {
      val approxSample = rows
        .map {
          case IndexedRow(index, vec) => (index, vec)
        }
        .sampleByKey(withReplacement = withReplacement, fractions, seed = seed)
      return approxSample
    }

    val rows: RDD[IndexedRow] = sc.parallelize(Array(
        IndexedRow(0, Vectors.dense(1.0, 2.0)),
        IndexedRow(1, Vectors.dense(4.0, 5.0)),
        IndexedRow(1, Vectors.dense(7.0, 8.0))))

    val fractions: Map[Long, Double] = Map(0L -> 1.0, 1L -> 0.5)
    println(takeStratSample(rows, fractions, false, 9L).collect().mkString(", "))
  }
}
