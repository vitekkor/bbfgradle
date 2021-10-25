// Original bug: KT-19389

object Foo2 {
    operator fun Any?.get(key: String) = key
}

fun <T, R> with2(receiver: T, block: T.() -> R): R = receiver.block()

object Main {
    val Int.id: Int? get() = 42

    fun bar(): String = with2(Foo2) {

        val x = object {
            val y = 38["Hello!"]
            val z = 45.id
        }
        x.y
    }
}

fun box(): String = Main.bar()
fun main(args: Array<String>) {
    box()
}
