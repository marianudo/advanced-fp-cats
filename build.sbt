name := "advanced-scala-cats"

organization := "com.marianonavas"

version := "0.0.1"

scalaVersion := "2.11.8"

lazy val catsVersion = "0.9.0"

lazy val catsDependencies = Seq(
  "cats-core",
  "cats-free"
).map { c =>
  "org.typelevel" %% c % catsVersion withSources() withJavadoc()
}

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc()
) ++ catsDependencies

initialCommands in console := "import ch04.FreeMonad.KeyValueDsl._"
