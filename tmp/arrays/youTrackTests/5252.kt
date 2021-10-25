// Original bug: KT-34156

interface A {
    fun foo() = 1
}

interface B: A {
    override fun foo()  = 2
    fun bar() {}
}

fun foo(x: Any): Any? {
    if (x is A) {
        if (x is B) {
            x.bar() // OK
            return x.foo() // is resolved in the IDE to A.foo, but there is smartcast to B
        }
    }
    return null
}

fun main() {
    println(foo(object : B {})) // 2
}
