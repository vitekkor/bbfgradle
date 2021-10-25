// Original bug: KT-9474

interface A{
    fun foo(x : Int = 1, y : Int)
}

interface B {
    fun foo(x : Int, y : Int = 2)
}

object C : A, B {
    override fun foo(x: Int, y: Int) {
        print(x)
        print(y)
    }
}

fun main(args: Array<String>) {
    C.foo()
}
