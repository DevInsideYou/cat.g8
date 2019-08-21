package $package;format="lower,package"$
package $project;format="lower,word"$
package $usecase;format="lower,package"$

object $db;format="Camel"$EntityGateway extends (() => EntityGateway) {
  final override def apply: EntityGateway =
    new $db;format="Camel"$EntityGateway

  final private class $db;format="Camel"$EntityGateway extends EntityGateway
}
