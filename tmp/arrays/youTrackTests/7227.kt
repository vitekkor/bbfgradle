// Original bug: KT-28369

fun f() {
    var t2: Boolean? = true
    if (t2 != null) {
        false || when { else -> {t2 = null; true} }
        t2.not() // wrong smart cast to not null, NPE
    }
}
