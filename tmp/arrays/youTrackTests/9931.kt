// Original bug: KT-6438

class A

fun A.foo() {} // 1
fun foo() {} // 2

fun test() {
    fun A.foo() {} // 3
    fun foo() {} // 4

    with(A()) {
        foo() // resolved to 4
        this.foo() // ambiguity: 1 and 3
    }
}
