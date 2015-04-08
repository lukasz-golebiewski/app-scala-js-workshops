package theapp

import akka.actor.ActorLogging
import spray.httpx.encoding.Gzip
import spray.routing.HttpServiceActor

class RoutingActor extends HttpServiceActor with ActorLogging with Routing {

  def receive = runRoute {
    compressResponse(Gzip) {
      myRoute
    }
  }

}
