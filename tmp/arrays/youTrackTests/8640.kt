// Original bug: KT-19542

fun main(args: Array<String>) {
    val b = B()

    println(b.getFooA())
    println(b.getFooB())
    println(b.getBarA())
    println(b.getBarB())
}

open class A {
    open val foo by lazy {
        "A.foo"
    }

    private val bar by lazy {
        "A.bar"
    }

    fun getBarA() = bar
}

class B : A() {
    override val foo by lazy {
        "B.foo"
    }

    private val bar by lazy {
        "B.bar"
    }

    fun getFooB() = foo
    fun getFooA() = super.foo
    fun getBarB() = bar
}
