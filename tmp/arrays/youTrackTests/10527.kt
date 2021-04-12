// Original bug: KT-4656

fun main(args : Array<String>) {
  var foo = { 1 }
  var bar = 1

  val t = { "${foo()} $bar" }
  fun b() = "${foo()} $bar"
  
  foo = { 2 }
  bar = 2
  
  println(t())
  println(b())
}
