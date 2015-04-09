package theapp

case class Message(subject: String, timestamp: Long = System.currentTimeMillis())

trait Api {
  def greet(name: String): Message
}

