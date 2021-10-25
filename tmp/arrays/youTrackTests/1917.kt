// Original bug: KT-35006

fun main() {
    with {
        first()
    }
}

inline fun with(block: () -> Any): Any {
    return block()
}

inline fun first(): Any {
    throw Exception()
}
