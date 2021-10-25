// Original bug: KT-10268

interface A<X, Y>
interface B<in X, Y>
interface Inv<I>

fun <Z> foo(a: A<in Long, Z>): Inv<Z> = null!!
fun <Z> foo(a: B<Long, Z>): Inv<Z> = null!!

fun <T> someA(): A<T, T> = null!!
fun <T> someB(): B<T, T> = null!!

fun test() {
    val fooA = foo(someA())// type fooA is Inv<in Long>, but expexted Inv<Long>
    val fooB = foo(someB()) // ok. Type is Inv<Long>
}

