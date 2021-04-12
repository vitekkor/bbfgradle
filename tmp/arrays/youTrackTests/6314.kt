// Original bug: KT-30532

class A {
    var prop: Foo? = Foo()

    tailrec fun tail(s: String): String {
        return tail(prop?.foo("".bar()) ?: nothing())
    }
}

class Foo {
    fun foo(s: String) = ""
}

inline fun String.bar(): String = this

fun nothing(): Nothing = throw Exception()
