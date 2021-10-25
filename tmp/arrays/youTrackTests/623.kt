// Original bug: KT-43159

class Value<T>(val value: T) {
    inline fun runBlock(block: (T) -> Unit) {
        block(value)
    }
}

fun <T> Value<Array<T>>.foo(index: Int) {
    runBlock { it ->
        index in it.indices
    }
}

fun main() {}
