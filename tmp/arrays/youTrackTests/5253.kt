// Original bug: KT-34156

@Experimental(Experimental.Level.WARNING)
@Target(AnnotationTarget.FUNCTION)
annotation class ExperimentalAPI

interface A {
    fun foo() = 1
}

interface B: A {
    @ExperimentalAPI
    override fun foo()  = 2
    fun bar() {}
}

fun foo(x: Any): Any? {
    if (x is A) {
        if (x is B) {
            x.bar() // x is resolved to B
            return x.foo() // no warning about experimental usage (because x is resolved to A)
        }
    }
    return null
}

fun main() {
    val x = foo(object : B {}) // no warnings
    println(x) // 2
}
