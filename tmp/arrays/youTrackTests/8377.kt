// Original bug: KT-17274

interface A {
    fun foo(vararg ci: Int) {
        println("A::foo")
        for (i in ci)
            println(i)
    }

    fun bar(ci: IntArray) {
        println("A::bar")
        for (i in ci)
            println(i)
    }
}

class B : A {
    override fun foo(ci: IntArray) { //overrides vararg 
        println("B::foo")
        for (i in ci)
            println(i)
    }

    override fun bar(vararg ci: Int) { //overrides IntArray
        println("B::bar")
        for (i in ci)
            println(i)
    }
}

fun testFoo() {
    val b = B()
    b.foo(intArrayOf(1, 2, 3))

    val a: A = b
    a.foo(1, 2, 3)
}

fun testBar() {
    val b = B()
    b.bar(1, 2, 3)

    val a: A = b
    a.bar(intArrayOf(1, 2, 3))
}

fun main(args: Array<String>) {

    testFoo()
    testBar()

}
