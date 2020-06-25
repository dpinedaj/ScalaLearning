package examples.module_5

import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.tuning.CrossValidatorModel
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.classification.RandomForestClassificationModel

//Own objects
import GridSearch.{cvModel}

object CheckResults {

  implicit class BestParamMapCrossValidatorModel(cvModel: CrossValidatorModel) {
    def bestEstimatorParamMap: ParamMap =
      cvModel.getEstimatorParamMaps.zip(cvModel.avgMetrics).maxBy(_._2)._1
  }
  def main(args: Array[String]): Unit = {
    println("Best Estimator ParamMap: \n" + cvModel.bestEstimatorParamMap)

    val bestPipelineModel =
      cvModel.bestModel.asInstanceOf[PipelineModel]

    println("Best Pipeline stages: \n" + bestPipelineModel.stages)

    val bestRandomForest =
      bestPipelineModel.stages(4).asInstanceOf[RandomForestClassificationModel]
    println("Best Random Forest Model: \n" + bestRandomForest.toDebugString)
    println(
      "Best Random Forest total num nodes: \n" + bestRandomForest.totalNumNodes
    )
    println(
      "Best Random Forest Feature Importance: \n" + bestRandomForest.featureImportances
    )
  }
}
