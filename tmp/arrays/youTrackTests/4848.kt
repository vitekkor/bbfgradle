// Original bug: KT-28774

fun main(args: Array<String>) {
    println("With: " + withLet(FooImpl()))
    println("Without: " + withoutLet(FooImpl()))
}

// Invalid "remove redundant let" reported here
fun withLet(foo: Foo) = foo.count.let { Pair(foo.temp(), it) }
fun withoutLet(foo: Foo) = Pair(foo.temp(), foo.count)

interface Foo {
    val count: Int
    fun temp(): Int
}

class FooImpl : Foo {
    override var count = 0
    override fun temp() = count++
}
