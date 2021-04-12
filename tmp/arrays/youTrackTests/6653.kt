// Original bug: KT-19389

object Foo2 {
    operator fun Any?.get(key: String) = 42 as Any
}

object Main {
    val Int.id: Int? get() = 42

    fun bar() = with(Foo2) {

        val x = object {
            val y = 38["Hello!"]
            val z = 45.id
        }
        println(x)
    }
}

fun main(args: Array<String>) {
    Main.bar()
}
