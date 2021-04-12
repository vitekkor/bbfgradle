// Original bug: KT-36793

inline fun <reified X> foo(): Foo = object : Foo {
  override fun bar(baz: Any): Boolean = baz is X
}

interface Foo {
    fun bar(baz: Any): Boolean
}

fun main() {
  foo<Int>()
  foo<String>()
}
