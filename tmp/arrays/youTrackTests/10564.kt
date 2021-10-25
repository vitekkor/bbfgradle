// Original bug: KT-4173

open class Z(val s: Int) {
    open fun a() {}
}
open class C(f: Z)

class B(val x: Int) {
    fun foo() {
        class X : C(Z(x)) {}
        X()
    }
}

fun main(args: Array<String>) {
    B(1).foo()
}
