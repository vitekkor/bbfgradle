// Original bug: KT-26346

fun main(args: Array<String>) {
    enum(Color.BLUE)
}

inline fun <reified E : Enum<E>> enum(default: E) = object {
    fun foo(): E = enumValueOf<E>("BLUE") ?: default
}

enum class Color { BLUE }
