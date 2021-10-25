// Original bug: KT-37877

class Scope {

    operator fun <T> Foo<T>.invoke(): T? = evaluator()
}

class Foo<T>(val evaluator: Scope.()->T?)

class Bar {
    val foo: Foo<Baz> = Foo { Baz() }
}

class Baz

fun main() {
    val bar: Bar? = Bar()
    Scope().apply {
        bar?.foo() ?: return@apply
        print("executed")
    }
}
