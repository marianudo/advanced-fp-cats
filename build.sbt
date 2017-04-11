name := "advanced-scala-cats"

organization := "com.marianonavas"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),

  // Cats
   "org.typelevel" %% "cats-core" % "0.7.2" withSources()
)
