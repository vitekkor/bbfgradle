// Original bug: KT-37057

@file:Suppress("RESERVED_MEMBER_INSIDE_INLINE_CLASS")

inline class Z(val data: Int) {
    override fun equals(other: Any?): Boolean {
        println(other is Z)
        return other is Z &&
                data % 256 == other.data % 256
    }
}

fun main() {
    if (Z(0) != Z(256)) println("FAIL")
}
