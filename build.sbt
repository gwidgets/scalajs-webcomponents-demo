import Dependencies._
import org.scalajs.core.tools.linker.backend.OutputMode
import org.scalajs.core.tools.linker.standard._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "simple-scalajs-app",
    libraryDependencies += scalaTest % Test
  )


enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.5"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.6.5"
val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "io.circe" %%% "circe-core",
  "io.circe" %%% "circe-generic",
  "io.circe" %%% "circe-parser"
).map(_ % circeVersion)


addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)

scalaJSLinkerConfig ~= { _.withOutputMode(OutputMode.ECMAScript2015) }

mainClass in Compile := Some("example.Main")

scalacOptions += "-P:scalajs:sjsDefinedByDefault"