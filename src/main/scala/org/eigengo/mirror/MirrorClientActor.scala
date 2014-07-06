package org.eigengo.mirror

import akka.actor.Actor
import com.google.api.client.auth.oauth2.Credential
import com.google.api.services.mirror.Mirror
import com.google.api.services.mirror.model.TimelineItem

object MirrorClientActor {
}

class MirrorClientActor extends Actor {
  import org.eigengo.mirror.ClientComponents._

  def mirror(credential: Credential) = new Mirror.Builder(transport, jsonFactory, credential)
      .setApplicationName("Activator Mirror")
      .build()

  def receive: Receive = {
    case (c: Credential, t: TimelineItem) =>
      sender ! mirror(c).timeline().insert(t).execute().getId
  }
}
