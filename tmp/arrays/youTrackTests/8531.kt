// Original bug: KT-18775

class Foo {
    fun foo() {
        println(C.aaa)  // breakpoint
    }

    private object C {
        const val aaa = "aaa"
    }
}

fun main(args: Array<String>) {
    Foo().foo()
}
