// Original bug: KT-33358

fun <T> foo(num: T): String = when (num) {
        is Int -> "int"
        is Double -> "dbl"
	else -> "else"
}


fun main() {
    val a = 5
    val d = 3.141593
    println(foo(a))
    println(foo(d))
}
