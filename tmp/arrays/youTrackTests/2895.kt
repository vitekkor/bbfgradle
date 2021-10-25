// Original bug: KT-40269

fun <T> foo(f: () -> T) {}
fun bar(g: () -> Unit) {}
fun <K> baz(): () -> K = TODO()
fun test() {
    foo { bar(baz()) } // [UNSUPPORTED_FEATURE] The feature "unit conversion" is disabled
}
