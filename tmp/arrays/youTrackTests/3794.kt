// Original bug: KT-7252

open class A {
    @JvmOverloads
    open fun foo(x: String = "OK") {
        println(x)
    }
}

class B : A() {
    fun foo() {  // <-- should be error
        println("FAIL")
    }
}
