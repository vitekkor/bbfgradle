// Original bug: KT-38337

inline class Wrapper(val id: Int)

data class InlineKey(val delegate: Map<Wrapper, String>) : Map<Wrapper, String> by delegate

fun main() {
    val id = 1234
    val wrap = Wrapper(id)

    val keys = InlineKey(mutableMapOf(wrap to "value"))
    println(keys[wrap])
}
