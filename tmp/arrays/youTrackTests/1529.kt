// Original bug: KT-28017

suspend fun foo(a: Int) {}
suspend fun bar(b: Int) {}
suspend fun baz(c: Int) {}

suspend fun quix(a: Int, b: Int, c: Int): Int {
    foo(a)
    bar(b)
    baz(c)
    return 42
}
