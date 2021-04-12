// Original bug: KT-26315

interface Foo<T> {
    fun foo(t: T)
}

val foo = object: Foo<Int> {
    override fun foo(t: Int) {}
}

fun main(args: Array<String>) {
    val a = arrayOf(1)
    foo.foo(a[0])
}
