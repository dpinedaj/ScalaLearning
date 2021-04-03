import sbt.complete.Parsers._

name := "Simple Spark Project"
version := "1.0"
scalaVersion := "2.12.11"
val sparkVersion = "2.4.0"

//Spark modules
val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion withSources ()
val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion withSources ()
val sparkMlLib =
  "org.apache.spark" %% "spark-mllib" % sparkVersion withSources ()
val sparkHive = "org.apache.spark" %% "spark-hive" % "2.4.4" % "provided"
val sparkStreaming =
  "org.apache.spark" %% "spark-streaming" % sparkVersion withSources ()
val sparkStreamingTwitter =
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0"
val twitter4j = "org.twitter4j" % "twitter4j-core" % "3.0.3" withSources ()
val twitter4jStream = "org.twitter4j" % "twitter4j-stream" % "3.0.3"

//Todo check test for hive
val hiveVersion = "1.2.2"
val hadoopVersion = "2.7.7"
val hiveCore = "org.apache.hive" % "hive-exec" % hiveVersion
val hadoopCom = "org.apache.hadoop" % "hadoop-common" % hadoopVersion
val apHttp = "org.apache.httpcomponents" % "httpclient" % "4.3.4"
val hadoopClient = "org.apache.hadoop" % "hadoop-client" % hadoopVersion
val hiveService = "org.apache.hive" % "hive-service" % hiveVersion
val hiveCli = "org.apache.hive" % "hive-cli" % hiveVersion

val hiveMetastore = "org.apache.hive" % "hive-metastore" % hiveVersion
val hiveExec = "org.apache.hive" % "hive-exec" % "1.2.2"
val pentaho =
  "org.pentaho" % "pentaho-aggdesigner-algorithm" % "5.1.5-jhyde" % Test

val myResolvers = Seq(
  Resolver.mavenLocal,
  "Cascading repo" at "http://conjars.org/repo"
) //define main project
lazy val main = (project in file("."))
  .aggregate(utils)
  .dependsOn(utils)
  .settings(
    name := "main",
    libraryDependencies ++= Seq(
      sparkCore,
      sparkSql,
      sparkMlLib,
      //sparkStreaming,
      //sparkHive,
      //sparkStreamingTwitter,
      //twitter4j,
      //twitter4jStream
    )
  )

//define utils
lazy val utils = (project in file("utils"))
  .settings(
    name := "utils"
  )
