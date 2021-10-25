// Original bug: KT-34830

class Inv<A> {
    fun foo(a: A) {}
}

fun <A> Inv<A>.foo(a: A?) {}

fun <A> bar(f: (A?) -> Unit) {}

fun <A> test(b: Inv<A>) {
    bar(b::foo) // Error in OI, ok in NI
}
