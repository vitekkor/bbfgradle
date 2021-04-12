// Original bug: KT-6439

class A

fun A.foo() {} // 1
fun foo() {} // 2

fun test() {
    fun A.bar() {} // 3
    fun bar() {} // 4

    with(A()) {
        foo() // resolved to 1
        this.foo() // resolved to 1
        bar() // resolved to 4
        this.bar() // resolved to 3
    }
}
