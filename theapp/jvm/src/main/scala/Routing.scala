package theapp

import spray.http.{StatusCodes, Uri}
import spray.routing.HttpService

trait Routing extends HttpService with CORSSupport {

  val pathToPublicAssets = "src/main/public/"

  def myRoute =
    cors {
      path("status") {
        get {
          complete("OK")
        }
      } ~ path("api") {
        reject
      }
    } ~
      pathEndOrSingleSlash {
        redirect(Uri("/index.html"), StatusCodes.MovedPermanently)
      } ~
      getFromBrowseableDirectory(pathToPublicAssets)


}
