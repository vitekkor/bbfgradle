// Original bug: KT-43273

import java.util.EnumSet

inline fun <reified E : Enum<E>> E.next(): E {
    val values = enumValues<E>()
    val ordinal = (this.ordinal + 1) % values.size
    return values[ordinal]
}

inline fun <reified E : Enum<E>> E.next(set: EnumSet<E>): E {
    var element = this
    do {
        element = element.next()
    } while (element !in set)
    return element
}
enum class E { A, B, C, D }
val set = EnumSet.of(E.A, E.C)!!

fun main() {
    println(E.A.next(set))
}
