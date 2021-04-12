// Original bug: KT-10970

fun box(): String {
    var r = "fail"
    map1 { r = "OK" } // should expand into: { { x } }
    return "OK"
}

inline fun map1(crossinline f: () -> Unit) {
    { map2(f) } ()
}

inline fun map2(crossinline f: () -> Unit) {
    { f() }()
}
