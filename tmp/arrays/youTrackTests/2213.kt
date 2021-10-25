// Original bug: KT-33641

fun main() {
    when (val x = Thing(42)) {
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
