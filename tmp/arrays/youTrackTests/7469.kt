// Original bug: KT-27300

class C {
    operator fun plusAssign(i: Int) {}
    operator fun plusAssign(b: Boolean) {}
}

fun f(b: Boolean) {
    val c = C()
    if (b)
        c += 1
    else
        c += true
}
