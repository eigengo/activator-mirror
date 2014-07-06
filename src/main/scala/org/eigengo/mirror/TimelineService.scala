package org.eigengo.mirror

import akka.actor.ActorRef
import com.google.api.services.mirror.model.{NotificationConfig, TimelineItem}
import spray.routing.{Directives, Route}

import scala.concurrent.ExecutionContext

object TimelineService extends Directives {
  import Authentication._
  import akka.pattern.ask
  import Timeouts.internalTimeout

  def route(mirrorActor: ActorRef)(implicit ctx: ExecutionContext): Route = {
    path("timeline") {
      authenticate(validCredential) { credential =>
        post {
          handleWith { body: String =>
            val t = new TimelineItem()
            t.setText(body)
            t.setNotification(new NotificationConfig().setLevel("DEFAULT"))
            (mirrorActor ? ((credential, t))).mapTo[String]
          }
        }
      }
    }
  }

}
