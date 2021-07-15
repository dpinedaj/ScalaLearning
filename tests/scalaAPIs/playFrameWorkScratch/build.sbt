name := "playFrameWorkScratch"
organization := "com.DanielFR"

version := "0.1"

scalaVersion := "2.13.5"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "mysql" % "mysql-connector-java" % "8.0.15",
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.DanielFR.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.DanielFR.binders._"