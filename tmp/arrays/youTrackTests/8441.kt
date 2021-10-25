// Original bug: KT-20450

fun main(args: Array<String>) {
    val x: String? = null
    foo(x) // Inline this function using Ctrl+Alt+N
}

fun foo(x: String?) {
    val y = x?.substring(22)
    if (y != null) {
        println(y)
        y.hashCode()
        if (x.startsWith("1")) {
            println(x.capitalize())
        }
    }
}
