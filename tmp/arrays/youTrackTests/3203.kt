// Original bug: KT-39349

enum class Foo(val x: String) {
    FIRST("1"), SECOND();

    constructor()

    fun foo() {
        println(x.length)
    }
}

fun main() {
    Foo.SECOND.foo()
}
