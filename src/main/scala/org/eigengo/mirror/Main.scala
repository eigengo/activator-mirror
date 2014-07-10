package org.eigengo.mirror

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import spray.routing.{HttpServiceActor, Route, RouteConcatenation}

import scala.io.StdIn

class Main(route: Route) extends HttpServiceActor {
  def receive: Receive = runRoute(route)
}

object Main extends App with RouteConcatenation {
  implicit val system = ActorSystem("Mirror")
  import system.dispatcher
  val mirror = system.actorOf(Props[MirrorClientActor])
  val service = system.actorOf(Props(new Main(AuthorisationService.route ~ TimelineService.route(mirror) ~ StaticService.route)))
  val io = IO(Http)
  io ! Http.Bind(service, interface = "0.0.0.0", port = 8080)
}
