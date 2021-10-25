// Original bug: KT-10472

interface A<T>
interface B<T> : A<T>

fun <T> foo(vararg t: T) {}
fun <T> foo(t: A<T>) {}
fun <T> foo(t: B<T>) {}

fun test(b: B<Int>) {
    foo(b) // resolve to foo(vararg T) but foo(B<T>) expected
}
