// Original bug: KT-28757

data class Foo(val name: String)
val X = Foo("X")
val Y = Foo("Y") 

fun test(x : Foo) {
  when(x) {
    X -> println("X")
    Y -> println("Y")
    X -> println("Z")
  }
}
