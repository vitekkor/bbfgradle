// Original bug: KT-33263

class A
class Foo<T>
class Bar<T>

fun <T> Foo<in T>.create(): Bar<in T> = Bar()
fun <T> convert(bar: Bar<in T>): Bar<T> = Bar()
fun test(x: Any) {}

fun crash(foo: Foo<A>) {
    test(convert(foo.create())) // internal error
}
