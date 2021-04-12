// Original bug: KT-39931

class Holder(val foo: Foo)
class Foo {
    val wrapper = SomeWrapper()
}

class SomeWrapper {
    operator fun invoke() = ""
}

fun someFun(list: List<Holder>) {
    list.map { it.foo.wrapper().length }
}
