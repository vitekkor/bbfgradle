// Original bug: KT-28547

class B<T> {
    inline fun bar(value: T) {}
}

class A {
    inline fun <T> foo(block: B<T>.() -> Unit) {}
}

fun main() {
  val a = A()
  val str = "xyz"
  a.foo<String>( { bar(str) } )
}
