package examples.module_2

// General imports for spark and logs
import org.apache.log4j.{Logger, Level}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

//Classes to do algebra operations
import org.apache.spark.mllib.linalg.{Vector, Vectors}

//Method to work with text in a dataframe
import org.apache.spark.ml.feature.{Tokenizer, RegexTokenizer}

//Classes to develop ML models using estimators
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.classification.LogisticRegressionModel

//Class to pass parameter settings to a model (estimator or transform)
import org.apache.spark.ml.param.ParamMap

/*combines a list of column into a single vector column
Accepts: all numeric types, boolean, vector*/
import org.apache.spark.ml.feature.VectorAssembler
import shapeless.Data

object SparkTransEstimators {
  Logger.getLogger("org").setLevel(Level.OFF)
  val sc: SparkContext = new SparkContext("local[*]", "transestimators")
  val spark: SparkSession = SparkSession.builder().getOrCreate()

  def main(args: Array[String]): Unit = {

    try {
      transforms
      estimators
      vectorAssembler
    } finally {
      this.sc.stop()
      this.spark.stop()
    }
  }

  def transforms: Unit = {

    def tokenizerMethod(df: DataFrame): DataFrame = {

      val tokenizer =
        new Tokenizer().setInputCol("sentence").setOutputCol("words")
      val tokenized = tokenizer.transform(df)
      return tokenized
    }

    val data = Seq(
      (0, "Hi I heard about Spark"),
      (1, "I wish Java could use case classes"),
      (2, "Logistic, regression, models,are,neat")
    )
    val sentenceDataFrame =
      this.spark.createDataFrame(data).toDF("label", "sentence")
    var df: DataFrame = tokenizerMethod(sentenceDataFrame)
    df.show()

  }

  def estimators: Unit = {

    def logisticRegressionTest(df: DataFrame): LogisticRegressionModel = {
      /*Create a logisticRegressionModel that needs to be fited to a
      vectors dataframe */
      val lr: LogisticRegression = new LogisticRegression()
      lr.setMaxIter(10).setRegParam(0.01)
      val model = lr.fit(df)
      return model

    }

    val training = MLUtils.convertVectorColumnsToML(
      this.spark
        .createDataFrame(
          Seq(
            (1.0, Vectors.dense(0.0, 1.1, 0.1)),
            (0.0, Vectors.dense(2.0, 1.0, -1.0)),
            (0.0, Vectors.dense(20, 1.3, 1.0)),
            (1.0, Vectors.dense(0.0, 1.2, -0.5))
          )
        )
        .toDF("label", "features")
    )

    val model = logisticRegressionTest(training)

    model.transform(training).show()

    def passParams(df: DataFrame): LogisticRegressionModel = {
      val lr: LogisticRegression = new LogisticRegression()
      val paramMap = ParamMap(lr.maxIter -> 20, lr.regParam -> 0.01)
      val model = lr.fit(df)
      model
    }

    val model2 = passParams(training)
    model2.transform(training).show()
  }

  def vectorAssembler: Unit = {
    def generateAssembler(df: DataFrame): DataFrame = {
      val assembler = new VectorAssembler()
        .setInputCols(Array("uniform", "normal1", "normal2"))
        .setOutputCol("features")

      val dfVec = assembler.transform(df)
      return dfVec
    }
    val dfRandom = spark
      .range(0, 10)
      .select("id")
      .withColumn("uniform", rand(10L))
      .withColumn("normal1", randn(10L))
      .withColumn("normal2", randn(11L))
    val df = generateAssembler(dfRandom)
    // An Example of a VectorAssembler
    df.select("id", "features").show()
  }
}
