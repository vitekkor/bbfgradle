// Original bug: KT-28291

open class C

private fun C.foo() = "hello from C.foo"

class D : C() {
    fun goo() {
        foo() // why does compiler allow a subclass to reference a private member of the superclass?
    }
}

fun main(args: Array<String>) {
    val d = D()
    d.foo() // even this works!
}
