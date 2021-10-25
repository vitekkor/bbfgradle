// Original bug: KT-31261

interface In<in E>
class A : In<A>
class B : In<B>

fun <T> select(x: T, y: T): T = x ?: y

fun foo(a: A, b: B) = select(a, b) // OK, no error, retun type is inferred to In<*>
