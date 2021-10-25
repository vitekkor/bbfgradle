// Original bug: KT-16480

interface I<T>
interface B<T>

val <T> B<T>.foo: I<T>.() -> Unit get() = null!!

fun <T> boo(f: B<T>.() -> Unit) {}

fun <E> test2(a: I<E>) {
    boo<E> {
        a.foo()   // Error:(12, 9) Kotlin: Type mismatch: inferred type is I<E> but I<T> was expected
        a.(foo)() // ok
    }
}
