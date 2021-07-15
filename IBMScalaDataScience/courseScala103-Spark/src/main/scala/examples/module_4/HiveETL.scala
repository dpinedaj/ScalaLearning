//package examples.module_4
//
//import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.streaming.StreamingContext
//import org.apache.spark.streaming.StreamingContext._
//import org.apache.spark.streaming.dstream.DStream
//import org.apache.spark.streaming.Seconds
//import org.apache.spark.streaming.scheduler.{
//  StreamingListener,
//  StreamingListenerReceiverError,
//  StreamingListenerReceiverStopped
//}
////import org.apache.spark.sql.hive.HiveContext
//
////Scala and java Utils
//import scala.util.control.NonFatal
//import java.net.{Socket, ServerSocket}
//import java.io.{File, PrintWriter}
//
////Own modules
//import examples.data.Flight
//import utils.MyFiles
//
//object HiveETL {
//
//  val defaultPort = 9000
//  val interval = Seconds(5)
//  val pause = 10 //miliseconds
//  val server = "127.0.0.1"
//  val hiveETLDir = "output/hive-etl"
//  val checkpointDir = "output/checkpoint_dir"
//  val runtime = 10 * 1000 // quit after N seconds
//  val numRecordsToWritePerBlock = 10000
//
//  def main(args: Array[String]): Unit = {
//
//    val port =
//      if (args.size > 0) args(0).toInt else defaultPort
//    val conf = new SparkConf()
//    conf.setMaster("local[*]")
//    conf.setAppName("ETL with Spark Streaming and Hive")
//    conf.set("spark.sql.shuffle.partitions", "4")
//    conf.set("spark.app.id", "HiveETL")
//    conf.set("spark.sql.warehouse.dir", "output/hive-warehouse")
//    conf.set("spark.sql.catalogImplementation", "hive")
//    conf.set("spark.sql.hive.metastore.version", "1.2.2")
//    conf.set("spark.sql.hive.metastore.jars","/opt/hive-1.2.2/lib/*")
//    val sc = new SparkContext(conf)
//
//    MyFiles.rmrf("derby.log")
//    MyFiles.rmrf("metastore_db")
//    MyFiles.rmrf(checkpointDir)
//    MyFiles.rmrf(hiveETLDir)
//
//    var ssc: StreamingContext = null
//    var dataThread: Thread = null
//
//    try {
//      dataThread = startSocketDataThread(port)
//      ssc = StreamingContext.getOrCreate(checkpointDir, createContext _)
//      ssc.addStreamingListener(new EndOfStreamListener(ssc, dataThread))
//      ssc.start
//      ssc.awaitTerminationOrTimeout(runtime)
//    } finally {
//      shutdown(ssc, dataThread)
//    }
//
//    def createContext(): StreamingContext = {
//      val ssc = new StreamingContext(sc, interval)
//      ssc.checkpoint(checkpointDir)
//
//      val dstream = readSocket(ssc, server, port)
//      processDStream(ssc, dstream)
//    }
//
//    def readSocket(
//        ssc: StreamingContext,
//        server: String,
//        port: Int
//    ): DStream[String] =
//      try {
//        println(s"Connecting to $server:$port...")
//        ssc.socketTextStream(server, port)
//      } catch {
//        case th: Throwable =>
//          ssc.stop()
//          throw new RuntimeException(
//            s"Failed to initialize server:port socket with" +
//              s"$server:$port $th"
//          )
//
//      }
//    def makeRunnable(port: Int) = new Runnable {
//      def run() = {
//        val listener = new ServerSocket(port);
//        val socket: Socket = null
//        try {
//          val socket = listener.accept()
//          val out = new PrintWriter(socket.getOutputStream(), true)
//          val inputPath =
//            "data/module_3_4/alaska-airlines/2008.csv"
//          var lineCount = 0
//          var passes = 0
//          scala.io.Source
//            .fromFile(inputPath)
//            .getLines()
//            .foreach { line =>
//              out.println(line)
//              if (lineCount % numRecordsToWritePerBlock == 0)
//                Thread.sleep(pause)
//              lineCount += 1
//            }
//        } finally {
//          listener.close()
//          if (socket != null) socket.close();
//        }
//      }
//    }
//    def startSocketDataThread(port: Int): Thread = {
//      val dataThread = new Thread(makeRunnable(port))
//      dataThread.start()
//      dataThread
//    }
//    def processDStream(
//        ssc: StreamingContext,
//        dstream: DStream[String]
//    ): StreamingContext = {
//      val hiveContext = new HiveContext(ssc.sparkContext)
//      import hiveContext.implicits._
//      import hiveContext.sql
//      import org.apache.spark.sql.functions._
//
//      val hiveETLFile = new java.io.File(hiveETLDir)
//      val hiveETLPath = hiveETLFile.getCanonicalPath
//      sql(s"""
//          CREATE EXTERNAL TABLE IF NOT EXISTS flights2 (
//              depTime           INT,
//              arrTime           INT,
//              uniqueCarrier     STRING,
//              flightNum         INT,
//              origin            STRING,
//              dest              STRING)
//            PARTITIONED BY (
//                depYear         STRING,
//                depMonth        STRING,
//                depDay          STRING)
//            ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
//            LOCATION '$hiveETLPath'
//          """).show
//      println("Tables")
//      sql("SHOW TABLES").show
//      dstream.foreachRDD { (rdd, timestamp) =>
//        try {
//          val flights =
//            rdd.flatMap(line => Flight.parse(line)).toDF().cache()
//
//          val uniqueYMDs = flights
//            .select("date.year", "date.month", "date.dayOfMonth")
//            .distinct
//            .collect
//
//          uniqueYMDs.foreach { row =>
//            val year = row.getInt(0)
//            val month = row.getInt(1)
//            val day = row.getInt(2)
//            val yearStr = "%04d".format(year)
//            val monthStr = "%02d".format(month)
//            val dayStr = "%02d".format(day)
//            val partitionPath =
//              "%s/%s-%s-%s".format(hiveETLPath, yearStr, monthStr, dayStr)
//
//            sql(s"""
//                ALTER TABLE flights2 ADD IF NOT EXISTS PARTITION (
//                    depYear='$yearStr',
//                    depMonth='$monthStr',
//                    depDay='$dayStr')
//                LOCATION '$partitionPath'
//                       """)
//            flights
//              .where(
//                $"date.year" === year and
//                  $"date.month" === month and
//                  $"date.dayOfMonth" === day
//              )
//              // DON'T write the partition columns
//              .select(
//                $"times.depTime",
//                $"times.arrTime",
//                $"uniqueCarrier",
//                $"flightNum",
//                $"origin",
//                $"dest"
//              )
//              .map(row => row.mkString("|"))
//              .rdd
//              .saveAsTextFile(partitionPath)
//
//            val showp = sql("SHOW PARTITIONS flight2")
//            val showpCount = showp.count
//            println(s"Partitions (${showpCount}):")
//            showp.foreach(p => println(" " + p))
//
//          }
//        } catch {
//          case NonFatal(ex) =>
//            sys.exit(1)
//        }
//      }
//      ssc
//    }
//
//  }
//  protected def shutdown(ssc: StreamingContext, dataThread: Thread) = {
//    println("Shutting down...")
//    if (dataThread != null) dataThread.interrupt()
//    else ("The dataThread is null!")
//    if (ssc != null)
//      ssc.stop(stopSparkContext = true, stopGracefully = true)
//    else ("The StreamingContext is null!")
//  }
//
//  class EndOfStreamListener(ssc: StreamingContext, dataThread: Thread)
//      extends StreamingListener {
//    override def onReceiverError(
//        error: StreamingListenerReceiverError
//    ): Unit = {
//      println(s"Receiver Error: $error. Stopping...")
//      shutdown(ssc, dataThread)
//    }
//    override def onReceiverStopped(
//        stopped: StreamingListenerReceiverStopped
//    ): Unit = {
//      println(s"Receiver stopped: $stopped. Stopping...")
//      shutdown(ssc, dataThread)
//    }
//  }
//}
//