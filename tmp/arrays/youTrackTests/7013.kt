// Original bug: KT-29194

fun dummy() {}

fun foo(b: Boolean?): Boolean {
    if (b == null) return false
    if (b) ::dummy else ::dummy
    return b // Type mismatch: inferred type is Boolean? but Boolean was expected
}
