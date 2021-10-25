// Original bug: KT-4174

open class C(f: () -> Unit)

class B(val x: Int) {
    fun foo() {
        class A : C({x}) {}
        A()
    }
}


fun main(args: Array<String>) {
    B(1).foo()
}
