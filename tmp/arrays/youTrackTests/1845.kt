// Original bug: KT-43521

fun main() {
  val topic = Topic<Listener>(Implementation())
  val listener = publisher(topic)
  listener("123")  // This line is mentioned below.
}

private class Topic<T>(val listener: T)

private fun <T> publisher(topic: Topic<T>): T = topic.listener

private interface Listener : (Any) -> Unit

private class Implementation : Listener {
  override fun invoke(p1: Any) {
    println(p1)
  }
}
