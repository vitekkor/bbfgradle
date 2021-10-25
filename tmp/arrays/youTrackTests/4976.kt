// Original bug: KT-34866

interface A {
    fun foo(x: Int, y: Int = 1)
    fun bar(x: String, y: String = "y from A")
}

interface B {
    fun foo(x: Int = 2, y: Int)
    fun bar(x: String = "x from B", y: String)
}

class AImpl : A, B {
    override fun foo(x: Int, y: Int) {
        println("x: $x, y: $y")
    }

    override fun bar(x: String, y: String) {
        println("x: $x, y: $y")
    }
}

fun main() {
    val a = AImpl()
    a.foo() // prints "x: 0, y: 1"
    a.bar() // fails with 
    //"java.lang.IllegalArgumentException: Parameter specified as non-null is null: method AImpl.bar, parameter x"
}
