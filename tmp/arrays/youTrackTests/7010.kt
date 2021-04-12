// Original bug: KT-28391

sealed class Foo

class Bar : Foo()
class Baz : Foo()

fun <T : Foo> test(foo : T): String {
    return when (foo as Foo) { // Without "as Foo", compiler complains that "'when' expression must be exhaustive".
        is Bar -> "bar"
        is Baz -> "baz"
    }
}
