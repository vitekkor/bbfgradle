// Original bug: KT-3448

fun main(args : Array<String>) {
    println("Hello, world!")
    println(Foo.bar)
}

object Foo {
    val bar = Bar.foo
    val baz = "foo"
}

object Bar {
    val foo = Foo.baz
}
