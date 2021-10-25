// Original bug: KT-29101


data class Foo(val bar: String?, val baz: Int?)

fun main() {
    val foo = Foo("bar", 123)
    validate(foo)
    println(foo.bar) // It won't work now.
}

fun validate(foo: Foo) {
    requireNotNull(foo.bar)
}
