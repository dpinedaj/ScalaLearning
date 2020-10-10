import sbt.complete.Parsers._

name := "Spark Streaming tests"
version := "1.0"
scalaVersion := "2.12.11"
val sparkVersion = "2.4.0"

val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion withSources ()
val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion withSources ()
val sparkStreaming =
  "org.apache.spark" %% "spark-streaming" % sparkVersion withSources ()
val streamingAkka = "org.apache.bahir" %% "spark-streaming-akka" % sparkVersion
val kafkaClient = "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion % "provided"
val kafkaStreaming = "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion
val kinesisIntegration = "org.apache.spark" %% "spark-streaming-kinesis-asl" % sparkVersion
val eventHub = "com.microsoft.azure" %% "azure-eventhubs-spark" % "2.3.16"

val myResolvers = Seq(
  Resolver.mavenLocal,
  "Cascading repo" at "http://conjars.org/repo",
  Resolver.bintrayRepo("cakesolutions", "maven")
)


lazy val main = (project in file("."))
  .settings(
    name := "main",
    libraryDependencies ++= Seq(
      sparkCore,
      sparkSql,
      sparkStreaming,
      streamingAkka,
      kafkaClient,
      kafkaStreaming,
      kinesisIntegration,
      eventHub
    )
  )