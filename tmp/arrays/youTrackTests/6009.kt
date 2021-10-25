// Original bug: KT-26129

fun main(args: Array<String>) {
    var bar :SomeClass?= null
    println("calling from null")
    bar?.foo()

    bar = SomeClass()
    println("calling from non null")
    bar.foo()
}

class SomeClass {
    fun foo() {
        println("foo call")
    }
}
