package examples.module_5

import org.apache.log4j.{Logger, Level}

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.StringIndexerModel
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator

//Own objects
import CreatingFeatures.{indexers, dA, dRFC, dfS}
import org.apache.spark.ml.classification.RandomForestClassifier

object CreatePipeline {

  Logger.getLogger("org").setLevel(Level.OFF)
  val tr = dfS.filter("Grant_Application_ID < 6635")
  val te = dfS.filter("Grant_Application_ID >= 6635")
  val training = tr.na.fill(0, Seq("PHDs"))
  val test = te.na.fill(0, Seq("PHDs"))

  val pipeline = definePipeline(indexers, dA, dRFC)
  val model = pipeline.fit(training)
  val pipeline_results = model.transform(test)
  def main(args: Array[String]): Unit = {
    println("Results of RF model: \n" + aucEval.evaluate(pipeline_results))
  }
  def definePipeline(
      indexers: Array[StringIndexerModel],
      assembler: VectorAssembler,
      rf: RandomForestClassifier
  ): Pipeline = {
    val pipeline = new Pipeline().setStages(
      indexers ++
        Array(assembler, rf)
    )
    return pipeline
  }

  def aucEval: BinaryClassificationEvaluator = {
    val auc_eval = new BinaryClassificationEvaluator()
      .setLabelCol("status")
      .setRawPredictionCol("rawPrediction")
    return auc_eval
  }
}
