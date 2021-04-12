// Original bug: KT-4173

open class Z {
    open fun a() {}
}
open class C(f: Z)

class B(val x: Int) {
    fun foo() {
        object : C(object: Z() {

            override fun a() {
                x
            }
        }) {}
    }
}

fun main(args: Array<String>) {
    B(1).foo()
}
