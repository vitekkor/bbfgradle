// Original bug: KT-45971

class A<out T, in E> {
    fun foo(x: @UnsafeVariance T) {}
    fun foo(x: @UnsafeVariance T, y: List<@UnsafeVariance T>): @UnsafeVariance E = null!!

    fun bar(): List<@UnsafeVariance E> = null!!
}

fun foo(x: A<String, Any?>, cs: CharSequence, ls: List<CharSequence>) {
    val y: A<CharSequence, String> = x

    y.foo(cs) // Inapplicable in FIR (probably argument type mismatch)
    val s: String = y.foo(cs, ls) // Inapplicable in FIR (probably argument type mismatch); Also, expected: List<String>, actual: Any?

    val ls2: List<String> = y.bar() // Expected: List<String>, actual: Any?
}
