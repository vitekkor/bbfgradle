// Original bug: KT-36816

interface Parent<T>

class Foo<T>(x: T?): Parent<T> {}
class Bar<T>(x: T): Parent<T> {}

fun <T> select(vararg x: T) = x[0]

fun <T> main(x: T) {
    val y = select(Foo(x), Bar(x)) // y is Parent<out T> in NI, Parent<T> in OI
}
