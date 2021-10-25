// Original bug: KT-9707

public open class Message()
public class Notification(val message: String) : Message()

public interface MessageRange<T : Message> : List<T>

public class MessageRangeImpl<T : Message>(val default: T) : AbstractList<T>(), MessageRange<T> {
  override val size: Int = 5

  override fun get(index: Int): T = default
}

public class MessageRangeDelegate<T : Message>(private val delegate: MessageRange<T>) : MessageRange<T> by delegate

fun main(args: Array<String>) {
  val range = MessageRangeImpl(Notification("Crash me"))
  val shared = MessageRangeDelegate(range)

  println(shared[2].message)
}
