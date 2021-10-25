// Original bug: KT-8125

interface A {
    fun foo() {
    }
}

interface B : A {
}

class D: B {
    override fun foo() {
        super.foo()
    }
}

fun main(args: Array<String>) {
    D().foo()
}
