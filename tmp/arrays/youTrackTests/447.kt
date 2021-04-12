// Original bug: KT-41357

open class Foo(val id: Int)

class CustomFoo : Foo(1)

fun test(): Boolean {
    val fooList = listOf(CustomFoo(), Foo(2))
    return fooList.first() is CustomFoo && fooList.last().id == 2 // ClassCastException
}

fun main() {
    check(test())
}
