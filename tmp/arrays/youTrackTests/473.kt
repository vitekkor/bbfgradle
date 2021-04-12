// Original bug: KT-41402

fun <T, R> Foo<T, R>.eq(x: R): String = "${this} eq $x"

class A(val n: Int)
class B<T, V> : Foo<T, V>
fun main() {
    val prop: Foo<A, Int> = B()
    val value: String = "foo"

    println(prop.eq(value))
}

interface Foo<T, out V>
