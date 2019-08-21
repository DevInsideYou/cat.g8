package $package;format="lower,package"$
package $project;format="lower,word"$
package $usecase;format="lower,package"$

trait Boundary

object Boundary extends (EntityGateway => Boundary) {
  final override def apply(gateway: EntityGateway): Boundary =
    new UseCase(gateway)

  final private class UseCase(gateway: EntityGateway) extends Boundary
}
