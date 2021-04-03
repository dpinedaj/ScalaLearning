name := "streaming"

version := "0.1"

scalaVersion := "2.12.11"


val AkkaVersion = "2.6.13"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
)


libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime