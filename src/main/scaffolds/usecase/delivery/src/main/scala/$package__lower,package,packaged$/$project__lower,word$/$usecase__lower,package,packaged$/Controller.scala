package $package;format="lower,package"$
package $project;format="lower,word"$
package $usecase;format="lower,package"$

trait Controller

object Controller extends (Boundary => Controller) {
  final override def apply(boundary: Boundary): Controller =
    new ControllerImplementation(boundary)

  final class ControllerImplementation (boundary: Boundary) extends Controller
}
