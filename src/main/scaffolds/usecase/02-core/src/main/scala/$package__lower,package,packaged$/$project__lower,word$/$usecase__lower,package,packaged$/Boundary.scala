package $package;format="lower,package"$
package $project;format="lower,word"$
package $usecase;format="lower,package"$

trait Boundary

object Boundary {
  def apply(gateway: EntityGateway): Boundary =
    new Boundary {}
}
