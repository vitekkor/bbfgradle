// Original bug: KT-31261

interface In<in E>
open class A : In<A>
open class B : In<B>

fun <T> select(x: T, y: T): T = x ?: y

fun foo(a: A, b: B) = select(a, b) // Error: implicit intersection type
