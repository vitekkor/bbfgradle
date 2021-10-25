// Original bug: KT-27454

interface I<T>

class B : I<CharSequence> {
    fun baz() {}
}

fun foo(x: I<*>) {
    if (x is B) {
        x.baz() // Smartcast inspection on `x` is not reported
    }
}
