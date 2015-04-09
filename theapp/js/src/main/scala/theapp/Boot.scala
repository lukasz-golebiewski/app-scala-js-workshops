package theapp

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLDivElement

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExport
import upickle._
import autowire._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Client extends autowire.Client[String, upickle.Reader, upickle.Writer] {
  def write[Result: Writer](r: Result) = upickle.write(r)

  def read[Result: Reader](p: String) = upickle.read[Result](p)

  override def doCall(req: Request) = {
    dom.ext.Ajax.post(
      url = "/api/" + req.path.mkString("/"),
      data = upickle.write(req.args)
    ).map(_.responseText)
  }
}


@JSExport
object Boot {

  @JSExport
  def main(content: HTMLDivElement): Unit = {

    val response = Client[Api].greet("John").call()

    response.onComplete {
      case Success(obj) => content.innerHTML = obj.toString
      case Failure(e) => throw new Exception("Parse exception", e)
    }

  }

}
