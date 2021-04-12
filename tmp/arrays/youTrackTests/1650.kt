// Original bug: KT-26708

interface A {
    fun foo() {
        { x }()
    }

    private companion object {
        val x = 45
    }
}

class B : A

fun main(args: Array<String>) {
    B().foo()
}
