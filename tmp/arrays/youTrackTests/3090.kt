// Original bug: KT-28370

fun f() {
    var t2: Boolean? = true
    if (t2 != null) { // or `t2 is Boolean`
        try {
            t2 = null
        } finally { }
        t2.not() // wrong smartcast, NPE
    }
}
