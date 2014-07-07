package org.eigengo.mirror

import akka.actor.ActorRefFactory
import spray.routing.{Route, Directives}

object StaticService extends Directives {

  def route(implicit arf: ActorRefFactory): Route = {
    getFromResourceDirectory("")
  }

}
