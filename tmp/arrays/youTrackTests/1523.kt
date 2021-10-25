// Original bug: KT-10970

fun test(x: Any) {
    map1 { x } // should expand into: { { x } }
}

inline fun anonymous(crossinline f: () -> Unit) {
    { f() }
}

inline fun map1(crossinline f: () -> Unit) {
    anonymous { map2(f) }
}

inline fun map2(crossinline f: () -> Unit) {
    anonymous { f() }
}
