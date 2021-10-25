// Original bug: KT-37877

operator fun Foo.invoke() = 42

class Foo {
//    operator fun invoke() = 42
}

class Bar {
    val foo: Foo = Foo()
}

fun main() {
    val bar: Bar? = Bar()
    bar?.foo()
    bar?.foo?.invoke()
}
