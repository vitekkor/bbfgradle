// Original bug: KT-33641

fun main() {
    val x = Thing(42)
    when (x) {
        Thing(42) -> {
            println("foo")
            x
        }
        else -> {
            println("bar")
        }
    }
}

inline class Thing(val value: Int)
