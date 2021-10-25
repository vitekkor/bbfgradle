// Original bug: KT-19251

data class SomeClass(val unit: Unit)

inline fun inlining(block: () -> Unit) {
    SomeClass(block())
}

fun noop() {
}

fun function() {
    inlining(::noop)
}
