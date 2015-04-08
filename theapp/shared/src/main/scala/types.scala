package theapp

case class Message(name: String)

trait Api {
  def hello(who: String): String
}
