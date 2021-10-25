// Original bug: KT-6718

class Foo(val bar: String, val sort: String)

fun main(args : Array<String>) {
  println(Foo::bar)   // This works
  println(Foo::sort)  // This doesn't - seems to search the global scope
}
