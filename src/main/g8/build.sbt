ThisBuild / version      := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion := "$scala_version$"

lazy val `$name;format="norm"$` =
  project
    .in(file("."))
    .aggregate(entities, core)
    .settings(
      name := "$name$"
    )

addCommandAlias("run", "main/run")

lazy val entities =
  project
    .in(file("entities"))

lazy val core =
  project
    .in(file("core"))
    .dependsOn(entities % oneToOneClasspathDependencies)

lazy val delivery =
  project
    .in(file("delivery"))
    .dependsOn(core % oneToOneClasspathDependencies)

lazy val persistence =
  project
    .in(file("persistence"))
    .dependsOn(core % oneToOneClasspathDependencies)

lazy val main =
  project
    .in(file("main"))
    .dependsOn(delivery % oneToOneClasspathDependencies)
    .dependsOn(persistence % oneToOneClasspathDependencies)

lazy val oneToOneClasspathDependencies: String =
  "compile->compile;test->test"

addCommandAlias("gen", "$name;format="norm"$/g8Scaffold")

onLoadMessage +=
  s"""\nRun \${green("gen usecase")} to generate new use cases.\n"""

def green(input: Any): String =
  scala.Console.GREEN + input + scala.Console.RESET
