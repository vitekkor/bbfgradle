// Original bug: KT-17171

class A<T> {
    fun foo(f: (T) -> Unit) {
    }
}

fun test(a: A<out Int>) { // a: A<C(out Int)> val it: Int = $1 // : C(out Int)
    a.foo { it } // type for it is Int
}
