// Original bug: KT-18775

class Foo {
    fun foo() {
        println(aaa)  // breakpoint
    }

    private companion object {
        const val aaa = "aaa"
    }
}

fun main(args: Array<String>) {
    Foo().foo()
}
