import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

val macwireVersion = "2.5.7"
val playWsStandaloneVersion = "2.1.10"

lazy val server = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-framework-template""",
    organization := "com.michaelmaysonet74",
    version := "1.1.0",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
      "com.softwaremill.macwire" %% "util" % macwireVersion,
      "com.typesafe.play" %% "play-ahc-ws-standalone" % playWsStandaloneVersion,
      "com.typesafe.play" %% "play-ws-standalone-json" % playWsStandaloneVersion
    )
  )
  // .settings(
  //   playDefaultPort := 9000
  // )
  .settings(
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map()
  )
