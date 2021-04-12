// Original bug: KT-26493

fun test(a: Foo) {
    if (a is Bar) takesBar(a) // Is transformed to (a as? Bar)?.let { takesBar(it) }
}

fun takesBar(b: Bar) {}
open class Foo
class Bar : Foo()
