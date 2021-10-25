// Original bug: KT-38437

fun foo(str: String) {}

fun x(buffer: CharArray) {
    val s = String(buffer) // resolved to java.lang.String
    foo(s) // so INAPPLICABLE_CANDIDATE here
}
