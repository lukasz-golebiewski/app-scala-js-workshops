package theapp

import spray.http.{StatusCodes, Uri}
import spray.routing.HttpService
import autowire._
import upickle._
import scala.concurrent.ExecutionContext.Implicits.global

object Server extends Api {
  override def hello(who: String): String = s"Hello to you, ${who}!"
}

object ApiServer extends autowire.Server[String, upickle.Reader, upickle.Writer] {
  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)

  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
}

trait Routing extends HttpService with CORSSupport {

  val pathToPublicAssets = "src/main/public/"

  def myRoute =
    cors {
      get {
        path("status") {

          complete("OK")

        }
      } ~ path("api" / Segments) { s =>
        extract(_.request.entity.asString) { e =>
          complete {
            ApiServer.route[Api](Server)(
              autowire.Core.Request(s, upickle.read[Map[String, String]](e))
            )
          }
        }
      }
    } ~
      pathEndOrSingleSlash {
        redirect(Uri("/index.html"), StatusCodes.MovedPermanently)
      } ~
      getFromBrowseableDirectory(pathToPublicAssets)


}
