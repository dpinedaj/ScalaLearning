package examples.module_2

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.util.MLUtils

object IdentifyOutliers {
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark: SparkSession = SparkSession
    .builder()
    .master("local[*]")
    .getOrCreate()
  import spark.implicits._
  def main(args: Array[String]): Unit = {

    try {
      val df = generateData
      println("DataFrame Base: ")
      df.show(5)
      val dfNorm = normalizeDf(df)
      println("\nDataFrame After Normalize:")
      dfNorm.show(5)

      val dfMahalobis = mahalobisDistance(dfNorm)
      removeOutliers(dfMahalobis)
    } finally {
      this.spark.close()
    }

  }

  def generateData: DataFrame = {
    val dfRandom = this.spark
      .range(0, 10)
      .select("id")
      .withColumn("uniform", rand(10L))
      .withColumn("normal1", randn(10L))
      .withColumn("normal2", randn(11L))

    val assembler = new VectorAssembler()
      .setInputCols(Array("uniform", "normal1", "normal2"))
      .setOutputCol("features")

    val dfVec =
      MLUtils.convertVectorColumnsFromML(assembler.transform(dfRandom))
    val dfOutlier = dfVec
      .select("id", "features")
      .unionAll(spark.createDataFrame(Seq((10, Vectors.dense(3, 3, 3)))))
    dfOutlier.sort(dfOutlier("id").desc)
  }
  def normalizeDf(df: DataFrame): DataFrame = {
    val scaler = new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeat")
      .setWithStd(true)
      .setWithMean(true)

    val scalerModel =
      scaler.fit(MLUtils.convertVectorColumnsToML(df.select("id", "features")))

    val dfScaled = scalerModel
      .transform(MLUtils.convertVectorColumnsToML(df))
      .select("id", "scaledFeat")
    dfScaled.sort(dfScaled("id").desc)
  }

  def mahalobisDistance(df: DataFrame): DataFrame = {

    //Libraries to work with outliers
    import org.apache.spark.mllib.stat.Statistics
    import breeze.linalg._

    println("\nChecking Mahalobis distance")
    val rddVec = MLUtils
      .convertVectorColumnsFromML(df.select("scaledFeat"))
      .rdd
      .map(_(0).asInstanceOf[org.apache.spark.mllib.linalg.Vector])

    val colCov = Statistics.corr(rddVec)
    //This is the inverse matrix from the correlation one from Breeze method
    val invColCovB = inv(new DenseMatrix(3, 3, colCov.toArray))

    // Computing Mahalanobis Distance

    val mahalanobis = udf[Double, org.apache.spark.ml.linalg.Vector] { v =>
      val k = v.toArray
      val vB = new DenseVector(k);
      vB.t * invColCovB * vB
    }

    val dfMahalanobis =
      df.withColumn("mahalanobis", mahalanobis(df("scaledFeat")))
    dfMahalanobis.show(5)
    return dfMahalanobis
  }
  def removeOutliers(df: DataFrame): Unit = {
    //Remove outliers checking the largest from df
    println("\nThe outliers are:")
    df.sort(df("mahalanobis").desc).show(2)

    val ids = df
      .select("id", "mahalanobis")
      .sort(df("mahalanobis").desc)
      .drop("mahalanobis")
      .collect()

    val idOutliers = ids.map(_(0).asInstanceOf[Long]).slice(0, 2)
    println("\nThe df after removing outliers")
    df.filter(s"id not in (${idOutliers.mkString(",")})").show()
  }
}
