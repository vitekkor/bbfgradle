// Original bug: KT-9362

class B(val a: Int) : A()

fun p(i: Int) {}

open class A {
    fun test() {
        if (this is B) {
            p(this.a) // ok
            p(a) // VerifyError
        }
    }
}
fun main(args: Array<String>) {
    B(5)
}
