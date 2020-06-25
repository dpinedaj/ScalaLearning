package examples.module_2

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

//library to transform data into vectorAssembler
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.functions._

//Normalizer libraries
import org.apache.spark.ml.feature.Normalizer
import org.apache.spark.ml.feature.StandardScaler
import shapeless.Data
import org.apache.spark.ml.feature.MinMaxScaler

object DataNormalization {
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark: SparkSession = SparkSession
  .builder()
  .master("local[*]")
  .getOrCreate()
  import spark.implicits._
  def main(args: Array[String]): Unit = {
    try {
      val df = generateData
      println("Data Before Normalizing")
      df.show(5)
      println("\nData After Normalize:")
      simpleNormalizer(df)
      standardScaler(df)
      minMaxScaler(df)
    } finally {
      this.spark.stop()
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
    val dfVec = assembler.transform(dfRandom)

    dfVec.select("id", "features")
  }

  def simpleNormalizer(df: DataFrame) = {
    //Normalize a dataset to have unit p-norm
    println("\nSimple Normalizer")
    val scaler1 = new Normalizer()
      .setInputCol("features")
      .setOutputCol("scaledFeat")
      .setP(1.0)
    scaler1.transform(df).show(5)
  }

  def standardScaler(df: DataFrame) = {
    //Normalize a dataset to have unit standard deviation and zero mean
    //It generates shuffling data
    println("\nStandard Scaler")
    val scaler2: StandardScaler = new StandardScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeat")
      .setWithStd(true)
      .setWithMean(true)

    val scaler2Model = scaler2.fit(df)
    /*It generates a transformer that can transform any dataframe
    applying the initial conditions */
    scaler2Model.transform(df).show(5)
  }

  def minMaxScaler(df: DataFrame) = {
    //Normalize data using a min-max range like [0-1]
    println("\nMin Max Scaler")
    val scaler3: MinMaxScaler = new MinMaxScaler()
      .setInputCol("features")
      .setOutputCol("scaledFeat")
      .setMin(-1.0)
      .setMax(1.0)

    val scaler3Model = scaler3.fit(df)
    scaler3Model.transform(df).show(5)
  }
}
