// Original bug: KT-3210

class Test {
    fun foo() {
        class Inner {
            fun f() {}
        }

        val i = Inner()
        i.f()

    }
}

fun main(args: Array<String>) {
    Test()
}
