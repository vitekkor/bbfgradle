// Original bug: KT-43273

inline fun <reified E : Enum<E>> E.next(set: Set<E>): E {
    var element = this
    do {
        element = enumValues<E>()[element.ordinal]
    } while (element !in set)
    return element
}
enum class E { A}

fun main() {
    println(E.A.next(emptySet()))
}
