// Original bug: KT-31151

interface Foo

enum class A : Foo
enum class B : Foo

fun foo(foo: Foo? = null): Foo =
    foo ?: when ("condition") {
        "1" -> enumValueOf<A>("foo")
        "2" -> enumValueOf<B>("foo")
        else -> error("")
    }
