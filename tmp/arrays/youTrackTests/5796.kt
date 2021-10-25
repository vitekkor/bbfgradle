// Original bug: KT-24585

class A(val a: String = "a")
class B(val b: String = "b")

fun main(args: Array<String>) {
    A().apply {
        B().apply {
            // Breakpoint! (evaluate 'a', get error message "'this@apply' is not captured")
            a
        }
    }
}
