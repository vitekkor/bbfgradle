// Original bug: KT-19391

object Foo {
    operator fun Int.get(key: String) = 42
}

fun bar() = with(Foo) {
    val x = object {
        val y = 38["Hello!"]
    }
}

fun main(args: Array<String>) {
    bar()
}
