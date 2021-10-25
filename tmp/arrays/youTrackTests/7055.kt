// Original bug: KT-29008

val a: Array<out String> = arrayOf("1", "2")
val b: Array<out String> = arrayOf("3", "4")
// val c = a + "3" // compile error
val c = arrayOf(*a, "3") // OK
// val d = a + b // compile error
val d = arrayOf(*a, *b) // OK
