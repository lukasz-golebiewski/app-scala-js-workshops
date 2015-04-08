package theapp

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import akka.io.Tcp.Bound
import spray.can.Http

import scala.concurrent.Future
import scala.util.Properties


object Backend extends App {

  implicit val system = ActorSystem("system")

  val apiRouterActorRef = system.actorOf(Props(new RoutingActor), "router")
  val port = Properties.envOrElse("PORT", "8080").toInt // for Heroku compatibility

  import system.dispatcher

  IO(Http) ! Http.Bind(apiRouterActorRef, "0.0.0.0", port)

}