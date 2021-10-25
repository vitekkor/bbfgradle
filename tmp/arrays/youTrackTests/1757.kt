// Original bug: KT-40396

val <C> C.foo get() = Foo<C>()
class Foo<C> { operator fun <T> invoke(body: () -> Unit) {} }

class Bar {
//    val baz = foo {}      // (1)
//    val baz = foo<Int> {} // (2)
}
