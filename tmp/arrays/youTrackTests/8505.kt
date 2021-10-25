// Original bug: KT-6327

enum class Foo {
  A;
  
  object d {
    val a = A
    val b = println("1")
  }
    
  companion object {
    val a = A
    val b = println("2")
  }
}

fun main(args : Array<String>) {
  println(Foo.d.a)
  println(Foo.A)
}
