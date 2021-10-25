// Original bug: KT-31733

open class Foo(val x: Int)
class Bar(val y: Int)

fun foo(bar: Bar): Foo {
    return object: Foo(bar.y) {}
}

fun main() {
    val x = foo(Bar(1))
    println(x.javaClass.declaredFields.joinToString { it.name })
}
