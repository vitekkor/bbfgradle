// Original bug: KT-28369

fun f() {
    var t2: Boolean? = true
    if (t2 != null && try { t2 = null; true } catch (e: Exception) { false }) {
        t2.not() // wrong smart cast to not null, NPE
    }
}
