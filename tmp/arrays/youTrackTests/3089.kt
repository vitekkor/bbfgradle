// Original bug: KT-28370

fun f() {
    var t2: Boolean? = true
    if (t2 != null) { // or `t2 is Boolean`
        try {
            throw Exception()
        } catch (e: Exception) {
            t2 = null
        }
        t2.not() // wrong smartcast, NPE
    }
}
