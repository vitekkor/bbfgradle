// Original bug: KT-38925

fun foo() {
    bar {
        val p = false
        baz(p, "".ifEmpty { "" })
    }
}

fun bar(f: suspend () -> Unit) {}
fun baz(p: Boolean, s: String?) {}
