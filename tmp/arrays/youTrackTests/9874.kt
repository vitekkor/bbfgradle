// Original bug: KT-11007

fun foo() {
    val s: String? = null
    val ss = if (true) {
        s?.length
    } else {
        s?.length
    }
    ss.hashCode() // Smart-cast to Int, should be type mismatch (Int? instead of Int)
}
