package examples.module_1


import org.apache.spark.SparkContext

//To set desired logs
import org.apache.log4j.Logger
import org.apache.log4j.Level

//Libraries to basic mllib statistics
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.linalg.{Matrix, Matrices}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary

//Library for random data in RDDS
import org.apache.spark.mllib.random.RandomRDDs._

object SparkStatistics {

  Logger.getLogger("org").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "Statistics")

    try {
      generalStatistics(sc)
      correlations(sc)
      randomRDD(sc)
    } finally {
      sc.stop()
    }
  }

  def generalStatistics(sc: SparkContext): Unit = {
    println("\nGeneral Statistics:")
    //Define a vectors RDD, is important to make analytics
    val observations: RDD[Vector] = sc.parallelize(
      Array(
        Vectors.dense(1.0, 2.0),
        Vectors.dense(4.0, 5.0),
        Vectors.dense(7.0, 8.0)
      )
    )
    //Define summary that grants many properties to ask to the data
    val summary: MultivariateStatisticalSummary =
      Statistics.colStats(observations)

    println("mean", summary.variance)
    println("variance", summary.variance)
    println("Nonzeros", summary.numNonzeros)
    println("L1 norm", summary.normL1)
    println("L2 norm", summary.normL2)

  }

  def correlations(sc: SparkContext): Unit = {
    println("\nCorrelations:")

    def correlateDoubles(x: RDD[Double], y: RDD[Double]) : Double = {
      Statistics.corr(x,y, "pearson")
    }

    def correlateVector(data : RDD[Vector], analysisType: String = "pearson"): Matrix = {
      Statistics.corr(data, analysisType)
    }

    val x: RDD[Double] = sc.parallelize(Array(2.0, 9.0, -7.0))
    val y: RDD[Double] = sc.parallelize(Array(1.0, 3.0, 5.0))

    println("Correlation Doubles", correlateDoubles(x, y))

    val data: RDD[Vector] = sc.parallelize(Array(
      Vectors.dense(2.0, 9.0, -7.0),
      Vectors.dense(1.0, -3.0, 5.0),
      Vectors.dense(4.0, 0.0, -5.0)))

    println("Correlation Matrix\n", correlateVector(data))


    println("\nCompare correlation Pearson vs Spearman:")

    val ranks: RDD[Vector] = sc.parallelize(Array(
      Vectors.dense(1.0, 2.0, 3.0),
      Vectors.dense(5.0, 6.0, 4.0),
      Vectors.dense(7.0, 8.0, 9.0)))

    println("Pearson\n", correlateVector(ranks))
    println("Spearman\n", correlateVector(ranks, "spearman"))

  }

  def randomRDD(sc: SparkContext): Unit = {

    /*
    Available distributions:
      -exponentialRDD
      -gammaRDD
      -logNormalRDD
      -normalRDD
      -poissonRDD
      -uniformRDD
      -exponentialVectorRDD
      -gammaVectorRDD
      -logNormalVectorRDD
      -normalVectorRDD
      -poissonVectorRDD
      -uniformVectorRDD*/

    println("\nRandomRDDs:")
    println("PoissonRDD")
    val million = poissonRDD(sc, mean=1.0, size=1000000L, numPartitions=10)
    println("mean", million.mean)
    println("variance", million.variance)

    println("NormalRDD")
    val data = normalVectorRDD(sc, numRows=10000L, numCols=3, numPartitions=10)
    val stats: MultivariateStatisticalSummary =  Statistics.colStats(data)

    println("mean", stats.mean)
    println("variance", stats.variance)
  }
}
