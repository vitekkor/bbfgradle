// Original bug: KT-6809

class Foo {
    fun fooFn() {}
}

fun main(args: Array<String>) {
    val f : Any = Foo()
    if (f is Foo) {
        val x = f
        x.fooFn() // error, x is of type Any, should be of type Foo
    }
}
