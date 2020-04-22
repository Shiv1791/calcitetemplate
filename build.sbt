import BuildSettings.BaseProject
import Dependencies._

name := "calcitetemplate"

version := "0.1"

scalaVersion := "2.12.11"

// Enable experimental cached SBT/Ivy library resolution to speed up compile
updateOptions := updateOptions.value.withCachedResolution(true)

val commonDeps = Seq(
  libraryDependencies ++=
    testDependencies(scalaTest, mockitoCore, calcite)
)
lazy val calcite_template = BaseProject("calcite-template")
  .settings(commonDeps)
  .settings(libraryDependencies ++= List(calcite))