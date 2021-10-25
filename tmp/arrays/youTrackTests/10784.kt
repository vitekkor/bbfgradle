// Original bug: KT-1826

class Foo(private val a: String) {
    // "a" is both constructor parameter and field. It is accessible from (almost) anywhere inside Foo.
    val x = a
    fun bar() = a
}
