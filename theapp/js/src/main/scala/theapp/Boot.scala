package theapp

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLDivElement

import scala.scalajs.js.annotation.JSExport


import org.scalajs.dom
import upickle._
import autowire._

import scala.concurrent.ExecutionContext.Implicits.global

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

    Client[Api].hello("Lukas").call().foreach(s => content.innerHTML = s)

  }

}
