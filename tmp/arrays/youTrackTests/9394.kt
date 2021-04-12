// Original bug: KT-13426

interface IA
interface IB

object A : IA {
    fun B.foo() { println("Yay!") }
}

object B : IB

fun test(a: IA, b: IB) {
    with(a) lambda1@{
        with(b) lambda2@{
            if (this@lambda1 is A && this@lambda2 is B) {
                foo()
            }
        }
    }
}
