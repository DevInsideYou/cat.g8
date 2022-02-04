package $package;format="lower,package"$
package $project;format="lower,word"$
package $usecase;format="lower,package"$

trait Controller

object Controller {
  def apply(boundary: Boundary): Controller =
    new Controller {}
}
