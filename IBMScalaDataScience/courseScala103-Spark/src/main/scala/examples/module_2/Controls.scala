package examples.module_2

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

//my own modules
import utils._

object Controls {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "Control")

    try {
      cachePersist(sc)
      checkpoint(sc)
    } finally {
      sc.stop()
      
    }
  }

  def cachePersist(sc: SparkContext): Unit = {
    /* Cache/Persist
            Cache saves an RDD in memory.
            Persist let's you vary where the data is saved (to memory or disk) */
    val inst1: RDD[Int] = sc.parallelize(0 until 5)
    inst1.cache() //Nothing happens until an action is called
    //Is the same as inst1.persist(MEMORY)

    val inst2: RDD[(Int, Int)] = inst1.map(i => (i, i * i))
    inst2.persist(StorageLevel.MEMORY_AND_DISK)

  }
  def checkpoint(sc: SparkContext): Unit = {
    /* Saves the RDD to the file system and forget his legacy*/
    
    val inst1: RDD[Int] = sc.parallelize(0 until 5)
    val inst2: RDD[(Int, Int)] = inst1.map(i => (i, i * i))
    val checkpointsDir: String = "output/module_2/checkpoints"
    MyFiles.clearDirectory(checkpointsDir)
    sc.setCheckpointDir(checkpointsDir)
    inst2.checkpoint
    inst2.isCheckpointed //false - not computed yet!
    inst2.getCheckpointFile //None -not computed yet!
    inst2.dependencies.head.rdd //1 dependency => inst1
    inst2.count //== 5 - forces evaluation
    inst2.isCheckpointed //true
    inst2.getCheckpointFile //Some(".../checkpoints/...")
    inst2.dependencies.head.rdd // Now a checkpointed RDD
  }

}
