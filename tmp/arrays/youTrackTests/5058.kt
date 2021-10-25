// Original bug: KT-34766

inline class InlineLong(val value: Long)
inline val Number.toInlineLong get() = InlineLong(this.toLong())

fun main() {
    val value = 0

    val withoutSubject = when (value.toInlineLong) {
        0.toInlineLong -> true
        else -> false
    }

    val withSubject = when (val subject = value.toInlineLong) {
        0.toInlineLong -> true
        else -> false
    }

    println(withoutSubject) // true
    println(withSubject) // false
}

