// Original bug: KT-13028

abstract class Foo<in I : Any, O : Any>

class Bar<in I : Any, O : Any>() : Foo<I, O>() {
    fun getOtherFoo(): Foo<*, O> = Bar<String, O>()
}

fun handle(foo: Foo<*, *>): List<Foo<*, *>> {
    foo as Bar<*, *>
    val other = foo.getOtherFoo()
    return listOf(other)
}

fun main(args: Array<String>) {
    handle(Bar<String, Unit>())
}
