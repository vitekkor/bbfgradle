// Original bug: KT-42441

class Foo

class Bar(foo: Foo, fooSeq: Sequence<Foo>)

private fun seq(): Any = sequence<Foo> {}

fun main() {
    val sequence = seq() as Sequence<Foo>
    Bar(Foo(), sequence)
}

