// Original bug: KT-37721

fun foo(vararg xs: Int, s1: String = "", s2: String = "") {}

fun use1(fn: (IntArray, String) -> Unit) {}

fun test() {
    use1(::foo) // TYPE_MISMATCH
}
