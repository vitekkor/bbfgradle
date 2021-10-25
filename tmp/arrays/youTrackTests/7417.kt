// Original bug: KT-27132

inline class Ucn(private val i: UInt)

interface Input<T> {
    fun peek(n: UInt = 0u): T
}

class RawInput(private val s: String) : Input<Ucn> {
    override fun peek(n: UInt): Ucn = if (n >= s.length.toUInt()) Ucn(0u) else Ucn(s[n.toInt()].toInt().toUInt())
}

fun main(args: Array<String>) {
    println(RawInput("test").peek())
}
