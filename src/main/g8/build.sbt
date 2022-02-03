import Dependencies._

ThisBuild / organization  := "$organization;format="lower,package"$"
ThisBuild / scalaVersion  := "$scala_version$"
ThisBuild / useSuperShell := false

lazy val `$name;format="norm"$` =
  project
    .in(file("."))
    .aggregate(domain, core, delivery, persistence, main)
    .settings(
      name := "$name$"
    )

lazy val commonSettings =
  compilerPlugins ++ commonScalacOptions ++ Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

lazy val compilerPlugins = Seq(
  addCompilerPlugin(com.olegpy.`better-monadic-for`),
  addCompilerPlugin(org.augustjune.`context-applied`),
  addCompilerPlugin(org.typelevel.`kind-projector`),
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions := {
    (Compile / console / scalacOptions)
      .value
      .filterNot(_.contains("wartremover"))
      .filterNot(Scalac.Lint.toSet)
      .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
  },
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

lazy val commonTestDependencies = Seq(
  libraryDependencies ++= Seq(
    com.github.alexarchambault.`scalacheck-shapeless_1.15`,
    org.scalacheck.scalacheck,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
    org.typelevel.`discipline-scalatest`,
  ).map(_ % Test),
)

lazy val domain =
  project
    .in(file("domain"))
    .settings(commonSettings)
    .settings(commonTestDependencies)

lazy val core =
  project
    .in(file("core"))
    .dependsOn(domain % oneToOneClasspathDependencies)
    .settings(commonSettings)
    .settings(commonTestDependencies)

lazy val delivery =
  project
    .in(file("delivery"))
    .dependsOn(core % oneToOneClasspathDependencies)
    .settings(commonSettings)
    .settings(commonTestDependencies)

lazy val persistence =
  project
    .in(file("persistence"))
    .dependsOn(core % oneToOneClasspathDependencies)
    .settings(commonSettings)
    .settings(commonTestDependencies)

lazy val main =
  project
    .in(file("main"))
    .dependsOn(delivery % oneToOneClasspathDependencies)
    .dependsOn(persistence % oneToOneClasspathDependencies)
    .settings(commonSettings)
    .settings(commonTestDependencies)

lazy val oneToOneClasspathDependencies: String =
  "compile->compile;test->test"

addCommandAlias("gen", "$name;format="norm"$/g8Scaffold")

onLoadMessage +=
  s"""\nRun \${green("gen usecase")} to generate new use cases.\n"""

def green(input: Any): String =
  scala.Console.GREEN + input + scala.Console.RESET
