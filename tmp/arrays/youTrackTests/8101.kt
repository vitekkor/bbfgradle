// Original bug: KT-21925

inline fun <X, Y> inlineFun(p: X, fn: (X) -> Y): Y {
    throw Exception()
    return fn(p)
}

class Subject {
    fun use() {
        inlineFun("abc") { it.length }
    }
}
