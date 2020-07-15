enablePlugins(ScriptedPlugin)

ThisBuild / scalaVersion := "2.12.12"
ThisBuild / useSuperShell := false
ThisBuild / autoStartServer := false

name := "Clean Architecture Template"

addCommandAlias("test", "g8Test")

scriptedLaunchOpts ++= Seq(
  "-Xms1024m",
  "-Xmx1024m",
  "-XX:ReservedCodeCacheSize=128m",
  "-Xss2m",
  "-Dfile.encoding=UTF-8"
)
