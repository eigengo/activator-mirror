package org.eigengo.mirror

import akka.actor.ActorRefFactory
import spray.routing.{Route, Directives}

object Ui extends Directives {

  def route(implicit arf: ActorRefFactory): Route = {
    getFromResourceDirectory("")
  }

}
