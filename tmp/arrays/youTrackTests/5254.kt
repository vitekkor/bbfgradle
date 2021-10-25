// Original bug: KT-28362

interface L2 {
    fun g() { }
    fun g2() {}
}

interface L1 {
    fun g() { }
    fun g1() {}
}

fun f(t: Any) {
    if (t is L1) {
        if (t is L2) {
            t.g2() // smart cast to L2
            t.g1() // smart cast to L1
            t.g() // smart cast to L2
        }
    }
}
