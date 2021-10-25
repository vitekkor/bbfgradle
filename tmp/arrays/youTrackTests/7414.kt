// Original bug: KT-27096

inline class Ucn(private val i: UInt)

class PPInput(private val s: ByteArray) {
    fun peek(n: UInt = 0u): Ucn? = if (n >= s.size.toUInt()) null else Ucn(s[n.toInt()].toUInt())
}
