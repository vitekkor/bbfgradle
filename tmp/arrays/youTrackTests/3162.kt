// Original bug: KT-39479

fun m2() {
    m({c -> f(c)}) // Convert lambda to reference not possible
}

fun m(l: C.(String) -> Unit) {
    val C = C()
    l(C,"s")
}

class C {
    fun f(s: String) {}
    fun f(i: Int) {}
}
