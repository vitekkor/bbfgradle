// Original bug: KT-14486

fun foo(s: String?) {
    var x: String? = null
    if (s != null) {
        x = s
    }
    if (x != null) {
        run {
            // Looks like smart cast is safe here
            x.hashCode()
        }
    }
}
