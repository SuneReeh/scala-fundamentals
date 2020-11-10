lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-fundamentals",
    description := "Exercises for the Pluralsight course 'Scala Fundamentals'",
    version := "0.1.0",

    scalaVersion := dottyLatestNightlyBuild.get
    //scalaVersion := "3.0.0-M2"
  )
