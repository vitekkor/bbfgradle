// Original bug: KT-28303

// test.kt
class Foo {
    fun printMe() {
        println("I'm foo")
    }
}

open class Bar {
    init {
        someMethod()
    }

    open fun someMethod() {
        //Doing nothing here
    }
}

class Baz : Bar() {
    val foo = Foo()
    override fun someMethod() {
        foo.printMe()
    }
}

fun main() {
    val baz = Baz() //It will fail here.
    baz.someMethod()
}
