// Original bug: KT-28369

fun f() {
    var t2: Boolean? = true
    if (t2 is Boolean && if (true) { t2 = null; true } else { false }) {
        t2.not() // wrong smart cast to not null, NPE
    }
}
