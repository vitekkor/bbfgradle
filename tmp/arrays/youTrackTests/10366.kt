// Original bug: KT-4946

fun main(args: Array<String>) {
    val a: Any = 1
    when (a) { // breakpoint on this line
        is String -> println() // first step end up here
        is Char -> {
            println()
            println() // !!!          second step ends up here
        }
        is Float -> println() // third step here
        is Double -> println()
        else -> println() // !!!               fourth step here
    }
    println() // fith step here
}
