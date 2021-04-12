// Original bug: KT-14011

fun main(args: Array<String>) {
    inline1 { inline2 { } }
}

inline fun inline1(crossinline action: () -> Unit) {
    action()
    inline2(action)
}

inline fun inline2(crossinline action: () -> Unit) = { action() }
