package theapp

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLDivElement

import scala.scalajs.js.annotation.JSExport

@JSExport
object Boot {

  @JSExport
  def main(content: HTMLDivElement): Unit = {
    content.innerHTML = "<h4>Hello World!</h4>"
  }

}
