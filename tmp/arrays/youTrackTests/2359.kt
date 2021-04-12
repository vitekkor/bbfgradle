// Original bug: KT-42099

class Foo<T : Number> {
    fun bar(p: @UnsafeVariance T?): T? = TODO()
    fun baz(p: T?): T? = TODO()
}
fun usage(a: Foo<*>) {
    a.bar(1.1) // OK
    // a.baz(1.1) // TYPE_MISMATCH
}
