package examples.module_4

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions._

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.util.MLUtils.{
  convertMatrixColumnsFromML => fromML,
  convertMatrixColumnsToML => toML
}
//Feature engineering
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}

//Logistic Regresion Estimator and summary method
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.classification.BinaryLogisticRegressionSummary
//Logistic Regression evaluator
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator

//Random Forest Estimator and model
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.classification.RandomForestClassificationModel

//Multiclass Classification evaluator
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Random Forest Regressor Estimator and model
import org.apache.spark.ml.regression.RandomForestRegressor
import org.apache.spark.ml.regression.RandomForestRegressionModel

object Evaluation {
  Logger.getLogger("org").setLevel(Level.OFF)
  val sc: SparkContext = new SparkContext("local[*]", "Linear Models")
  val spark: SparkSession = SparkSession.builder().getOrCreate()
  import spark.implicits._

  def main(args: Array[String]): Unit = {

    try {
      val df = loadData
      println("Initial DataFrame:")
      df.show(5)

      val splitData = df.randomSplit(Array(0.7, 0.3))
      val train = toML(splitData(0))
      val test = toML(splitData(1))

      //Logistic Regression
      //val predictLR = applyLogisticRegression(train, test)
      //predictLR.show()
      ////Evaluate Logistic Regression
      //val roc = evaluateLogisticRegression(predictLR)
      //println(s"Roc for Logistic Regression: $roc")

      //Random Forest Classifier
      val predictRFC = applyRandomForestClassifier(df, train, test)
      //Evaluate Random Forest Classifier
      val accuracy = evaluateRandomForest(predictRFC)
      println(s"Random Forest Classifier Accuracy: $accuracy")

      //Random Forest Regressor
      val predictRFR = applyRandomForestRegressor(df, train, test)
      //Evaluate Random Forest Regressor
      //val accuracyR = evaluateRandomForest(predictRFR)
      //println(s"Random Forest Regressor Accuracy: $accuracyR")

    } finally {
      this.sc.stop()
      this.spark.stop()
    }
  }

  def loadData: DataFrame = {
    val data = toML(
      MLUtils.loadLibSVMFile(this.sc, "data/sample_libsvm_data.txt").toDF()
    )
    return data
  }

  def applyLogisticRegression(train: DataFrame, test: DataFrame): DataFrame = {
    val logr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)

    val logrModel = logr.fit(train)
    val predictionsLogR = logrModel.transform(test)
    return predictionsLogR
  }

  def evaluateLogisticRegression(predictions: DataFrame): Double = {
    val evaluator = new BinaryClassificationEvaluator()
      .setLabelCol("label")
      .setRawPredictionCol("rawPrediction")
      .setMetricName("areaUnderROC")

    val roc = evaluator.evaluate(predictions)
    return roc
  }

  def applyRandomForestClassifier(
      data: DataFrame,
      train: DataFrame,
      test: DataFrame
  ): DataFrame = {
    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(data)
    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labels)
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(data)

    val rfC = new RandomForestClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeatures")
      .setNumTrees(3)
    val pipelineRFC = new Pipeline()
      .setStages(Array(labelIndexer, featureIndexer, rfC, labelConverter))
    val modelRFC = pipelineRFC.fit(train)
    val predictionsRFC = modelRFC.transform(test)
    return predictionsRFC
  }
  def applyRandomForestRegressor(
      data: DataFrame,
      train: DataFrame,
      test: DataFrame
  ): DataFrame = {
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(data)
    val rfR = new RandomForestRegressor()
      .setLabelCol("label")
      .setFeaturesCol("indexedFeatures")
    val pipelineRFR = new Pipeline().setStages(Array(featureIndexer, rfR))
    val modelRFR = pipelineRFR.fit(train)
    val predictions = modelRFR.transform(test)
    return predictions
  }
  def evaluateRandomForest(predictions: DataFrame): Double = {
    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
    return accuracy
  }

}
