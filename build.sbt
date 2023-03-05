import Dependencies._
import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "1.5.0"
ThisBuild / organization := "com.michaelmaysonet74"
ThisBuild / organizationName := "michaelmaysonet74"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-framework-template""",
    libraryDependencies ++= {
      Seq(
        scalaTest % Test,
        macwireMacros % Provided,
        macwireUtil,
        ws
      )
    },
    playDefaultPort := 9000
  )
  .settings(
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map(
      "APPLICATION_SECRET" -> sys.env.getOrElse("APPLICATION_SECRET", "")
    )
  )
