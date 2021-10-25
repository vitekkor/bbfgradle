// Original bug: KT-21598

class Foo {
    @Deprecated(message = "For binary compatibility only", level = DeprecationLevel.HIDDEN)
    fun bar() {}
}

fun Foo.bar() {} // WARNING: Extension is shadowed by a member: public final fun bar(): Unit
