// Original bug: KT-31670

class In<in T>

fun <T : Any> foo(inNullable: In<T?>, n: Number) {} // (1)
fun <T : Any> foo(inNonNull: In<T>, i: Int) {} // (2)

fun test(inString: In<String?>, i: Int) {
    foo(inString, i as Number) // OK, foo is resolved to (1)
}
