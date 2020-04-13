package $package;format="lower,package"$
package $name;format="lower,word"$

object Main extends App {
  // run `sbt gen usecase` to generate
  // one of these dependency graphs
  // and add them to the Seq
  Seq[DependencyGraph](
    // user.analytics.DependencyGraph.apply
  ).foreach(_ apply args)

  private[this] type DependencyGraph =
    Array[String] => Any
}
