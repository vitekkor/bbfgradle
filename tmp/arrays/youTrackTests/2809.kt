// Original bug: KT-28016

suspend fun foo(a: Int) {}
suspend fun bar(b: Int) {}

suspend fun baz(a: Int, b: Int): Int {
    foo(a)
    bar(b)
    return 42 // not a tail suspend call to bar
}
