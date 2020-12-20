ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version      := "0.1"
ThisBuild / organization := "io.diebold"

lazy val adventOfCode = (project in file("."))
  .settings(
    name := "AdventOfCode2020",
    description := "Advent of Code 2020",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.2",
      "org.scalatest" %% "scalatest" % "3.2.2" % "test",
    ),
  )
