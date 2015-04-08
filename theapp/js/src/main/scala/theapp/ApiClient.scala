package theapp

import org.scalajs.dom
import org.scalajs.dom.ext.Ajax
import upickle._
import autowire._
import scala.concurrent.ExecutionContext.Implicits.global

object ApiClient extends autowire.Client[String, upickle.Reader, upickle.Writer] {
  def write[Result: Writer](r: Result) = upickle.write(r)

  def read[Result: Reader](p: String) = upickle.read[Result](p)

  override def doCall(req: Request) = {
    dom.ext.Ajax.post(
      url = "/api/" + req.path.mkString("/"),
      data = upickle.write(req.args)
    ).map(_.responseText)
  }
}
