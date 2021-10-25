// Original bug: KT-9461

interface In<in I>

interface B : In<B>

interface C<I, T>

fun <I, T> In<I>.foo(f: () -> C<I, T>) {}

class E : B // : In<E>

fun test(c: C<E, Int>, e: E) {
    e.foo<E, Int> { c }
    e.foo { c } // error here: expected C<B, ???> but must be C<??? : B, ???>
}
