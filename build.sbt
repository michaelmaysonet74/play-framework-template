//import play.sbt.PlayImport.PlayKeys._
import com.typesafe.sbt.packager.docker._

lazy val server = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-framework-template""",
    organization := "com.michaelmaysonet74",
    version := "1.1.2",
    scalaVersion := "2.13.8",
    libraryDependencies ++= {
      val macwireVersion = "2.5.7"

      Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
        "com.softwaremill.macwire" %% "macros" % macwireVersion % Provided,
        "com.softwaremill.macwire" %% "util" % macwireVersion,
        ws
      )
    }
  )
  .settings(
    dockerExposedPorts ++= Seq(9000),
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerEnvVars := Map(
      "APPLICATION_SECRET" -> sys.env.getOrElse("APPLICATION_SECRET", "")
    )
  )
// .settings(
//   playDefaultPort := 9000
// )
