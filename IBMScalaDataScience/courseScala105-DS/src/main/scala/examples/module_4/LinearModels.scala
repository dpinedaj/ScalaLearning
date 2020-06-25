package examples.module_4

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.SparkContext

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.util.MLUtils.{
  convertVectorColumnsFromML => fromMl,
  convertVectorColumnsToML => toML
}

//Estimator and summary class for logistic Regression
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.classification.BinaryLogisticRegressionSummary

//Estimator to generate Linear Least Squares or Linear Regression
import org.apache.spark.ml.regression.LinearRegression

object LinearModels {
  Logger.getLogger("org").setLevel(Level.OFF)
  val sc: SparkContext = new SparkContext("local[*]", "Linear Models")
  val spark: SparkSession =
    SparkSession.builder().getOrCreate()
  import spark.implicits._
  def main(args: Array[String]): Unit = {

    try {
      val df = loadData
      println("Initial DataFrame:")
      df.show(5)
      val splitData = df.randomSplit(Array(0.7, 0.3))
      val train = toML(splitData(0))
      val test = toML(splitData(1))

      logisticRegression(train)

      linearLeastSquares(train)
    } finally {
      this.sc.stop()
      this.spark.close()
    }
  }

  def loadData: DataFrame = {
    val data = toML(
      MLUtils.loadLibSVMFile(this.sc, "data/sample_libsvm_data.txt").toDF()
    )
    return data
  }

  def logisticRegression(train: DataFrame): Unit = {
    println("\nLogistic Regression")
    val logr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)

    val logrModel = logr.fit(train)

    println(
      s"Weights: ${logrModel.coefficients}\nIntercept: ${logrModel.intercept}"
    )
    val trainingSummaryLR = logrModel.summary
    val objectiveHistoryLR = trainingSummaryLR.objectiveHistory
    println(objectiveHistoryLR)
  }

  def linearLeastSquares(train: DataFrame): Unit = {
    println("\nLinear Regression")
    val lr = new LinearRegression()
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)

    val lrModel = lr.fit(train)

    println(
      s"Weights: ${lrModel.coefficients}\nIntercept: ${lrModel.intercept}"
    )
    val trainingSummaryLLS = lrModel.summary
    val objectiveHistoryLLS = trainingSummaryLLS.objectiveHistory

    println(objectiveHistoryLLS)

    trainingSummaryLLS.residuals.show(3)
  }
}
