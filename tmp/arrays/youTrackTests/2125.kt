// Original bug: KT-40282

inline class Inline(val value: Any)

fun main() {
    mutableListOf(Inline(0), Inline(0))
        .sortWith { a, b -> (a.value as Int).compareTo(b.value as Int) }
}
