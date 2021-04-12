// Original bug: KT-4754

var i = 0
object Foo {
  val a = i++
}

var j = 0
val bar = object {
  val a = j++
}

fun main(args : Array<String>) {
  println("$i, $j")
  bar
  println("$i, $j")
  Foo
  println("$i, $j")
}
