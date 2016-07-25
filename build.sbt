name := "advanced-fp-cats"

organization := "me.marianonavas"

version := "0.0.1"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),
  "org.typelevel" %% "cats" % "0.6.1" withSources()
)

initialCommands := "import me.marianonavas.advancedfpcats._"

