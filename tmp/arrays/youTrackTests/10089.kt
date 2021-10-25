// Original bug: KT-3585

fun unit() {}
fun String.unit() {}
fun test(s: String?) {
    s?.unit()?:unit() // Wrong report, that elvis always returns left part. s?.unit() should have Unit? type, not Unit.
}

