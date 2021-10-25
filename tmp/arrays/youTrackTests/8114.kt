// Original bug: KT-17301

fun test() {
    val v = A(0)
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo() // 20 in one line
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()

            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo() // 20 in one line
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()

            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo() // 20 in one line
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()
            .foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo().foo()

    println(v.a)
}

class A(a_init: Int) {
    val a = a_init
    fun foo() = A(a+1)
}
