// Original bug: KT-13934

class A {
    fun foo() = "A"
    companion object {
        fun foo() = "A.Companion"
    }
}

fun process(call: () -> String) {
    println(call())
}

fun main() {
    process(A()::foo) // prints A
    process(A::foo)   // prints A.Companion
}
