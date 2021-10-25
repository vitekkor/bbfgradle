// Original bug: KT-7874

class A {
    class Nested() {
        fun f() = privateCompanionFunction()
    }
    companion object {
        private fun privateCompanionFunction() = println("java.lang.VerifyError at runtime")
    }
}

fun main(args: Array<String>) = A.Nested().f()
