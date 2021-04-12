// Original bug: KT-30947

abstract class Foo<T>(val v: T)
fun <T: Number> Foo<T>.foo() = v.toInt()

class LongFoo(v: Long): Foo<Long>(v)

fun Foo<*>.bar() {
    if (this is LongFoo) {
        this.foo() // OK, Smart cast to LongFoo, `this` needed due to KT-26264
    }
}

fun <T> Foo<T>.baz() {
    if (this is LongFoo) {
        this.foo() // compilation error
    }
}
