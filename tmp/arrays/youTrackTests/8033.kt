// Original bug: KT-23168

fun main() {
    foo { it.length }
    bar { it.size }
}

inline fun foo(crossinline f: (String) -> Unit) {
    bar { f(it[0]) }
}

fun bar(f: (Array<String>) -> Unit) {}
