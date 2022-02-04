package $package;format="lower,package"$
package $project;format="lower,word"$
package $usecase;format="lower,package"$

object $db;format="Camel"$EntityGateway {
  def apply: EntityGateway =
    new EntityGateway {}
}
