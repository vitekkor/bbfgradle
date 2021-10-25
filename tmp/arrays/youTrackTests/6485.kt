// Original bug: KT-15391

class Foo : () -> Int {
  override operator fun invoke() = 42
}

val foo1: () -> Int
  get() = Foo()
val foo2: () -> Int
  get() = Foo()::invoke

fun main() {
  println("Direct : ${foo1()}")
  println("Via Ref: ${foo2()}")
}
