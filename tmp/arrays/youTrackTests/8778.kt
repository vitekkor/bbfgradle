// Original bug: KT-17553

val arr = IntArray(20, 10::pow) // compiler crash
val arr1 = IntArray(20) { 10.pow(it) } // compiles
val arr2 = Array(20, ""::pow) // compiles

fun Int.pow(num: Int) = 5
fun String.pow(num: Int) = 3
