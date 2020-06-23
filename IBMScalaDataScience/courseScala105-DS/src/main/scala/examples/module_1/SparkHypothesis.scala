package examples.module_1

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

//To set desired logs
import org.apache.log4j.Logger
import org.apache.log4j.Level

//Libraries to define Vectors and Matrices types
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.linalg.{Matrix, Matrices}
import org.apache.spark.mllib.regression.LabeledPoint

//Library to check statistics
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.mllib.stat.KernelDensity

//Results
import org.apache.spark.mllib.stat.test.ChiSqTestResult
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult

//Random RDD with some distributions
import org.apache.spark.mllib.random.RandomRDDs.normalRDD
import org.apache.spark.mllib.random.RandomRDDs.uniformRDD

object SparkHypothesis {

  Logger.getLogger("org").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext("local[*]", "Hypothesis")
    try {
      chiSqrGoodnessFit
      chiSqrIndependence(sc)
      kolmogorov(sc)
      kernelDensity(sc)
    } finally {
      sc.stop()
    }
  }
  def checkChiSqr[T](
      vec: Vector = null,
      mat: Matrix = null,
      obs: RDD[LabeledPoint] = null
  ): Either[ChiSqTestResult, Array[ChiSqTestResult]] = {

    if (vec != null) {
      val value = vec
      return Left(Statistics.chiSqTest(value))

    } else if (mat != null) {
      val value = mat
      return Left(Statistics.chiSqTest(value))

    } else if (obs != null) {
      val value = obs
      return Right(Statistics.chiSqTest(value))
    } else {
      throw new Exception("There's not parameters")
    }
  }

  def chiSqrGoodnessFit = {
    println("\nChi squared To check goodness fit:")
    val vec: Vector = Vectors.dense(0.3, 0.2, 0.15, 0.1, 0.1, 0.1, 0.05)
    println(checkChiSqr(vec = vec).left.get)

  }
  def chiSqrIndependence(sc: SparkContext) = {
    println("\nChi squared to check independence of variables:")
    val mat: Matrix =
      Matrices.dense(3, 2, Array(13.0, 47.0, 40.0, 80.0, 11.0, 9.0))
    println(checkChiSqr(mat = mat).left.get)

    println("\nChi squared using RDD[LabeledPoint]")
    val obs: RDD[LabeledPoint] = sc.parallelize(
      Array(
        LabeledPoint(0, Vectors.dense(1.0, 2.0)),
        LabeledPoint(1, Vectors.dense(0.5, 1.5)),
        LabeledPoint(1, Vectors.dense(1.0, 8.0))
      )
    )

    println(checkChiSqr(obs = obs).right.get.mkString(", "))
  }

  def kolmogorov(sc: SparkContext): Unit = {
    println("\nKolmogorov:")
    def checkKolmog(
        data: RDD[Double],
        dist: String
    ): KolmogorovSmirnovTestResult = {
      return Statistics.kolmogorovSmirnovTest(data, dist, 0, 1)
    }
    val data: RDD[Double] =
      normalRDD(sc, size = 100, numPartitions = 1, seed = 13L)
    println(checkKolmog(data, "norm"))

    val data1: RDD[Double] =
      uniformRDD(sc, size = 100, numPartitions = 1, seed = 13L)
    println(checkKolmog(data1, "norm"))
  }

  def kernelDensity(sc: SparkContext): Unit = {
      println("\nKernelDensity:")
    def checkKernel(
        data: RDD[Double],
        bandWidth: Double,
        estimation: Array[Double]
    ): Array[Double] = {
      val kd = new KernelDensity().setSample(data).setBandwidth(bandWidth)
      kd.estimate(estimation)
    }
    val data: RDD[Double] = normalRDD(sc, size=1000, numPartitions=1, seed=17L)
    val estimation1: Array[Double] = Array(-1.5, -1, -0.5, 1, 1.5)
    println(checkKernel(data, 0.1, estimation1).mkString(", "))
    val estimation2: Array[Double] = Array(-0.25, 0.25, 0.5, 0.75, 1.25)
    println(checkKernel(data, 0.1, estimation2).mkString(", "))
  }
}

