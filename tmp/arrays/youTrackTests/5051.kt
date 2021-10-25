// Original bug: KT-33495

abstract class Receiver {
    abstract suspend fun operation(): Unit
}

inline fun inlineMe(crossinline op: suspend Receiver.() -> Unit) = object : Receiver() {
    override suspend fun operation() = op.invoke(this)
}

suspend fun dummy() {}

fun main() {
    inlineMe {
        dummy()
        dummy()
        dummy()
        dummy()
    }
}
