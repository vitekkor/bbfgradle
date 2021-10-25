// Original bug: KT-25030

class Foo {
    fun run() {
        Nested().foo()
    }

    private companion object {
        val x: Int = 42
    }

    private class Nested {
        fun foo() {
            println(x)
        }
    }
}

fun main(args: Array<String>) {
    Foo().run() // Compiles fine in 1.2, fails with IAE in 1.3
}
