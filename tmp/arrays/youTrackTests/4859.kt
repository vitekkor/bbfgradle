// Original bug: KT-18130

fun main() {
    var x: String?
    x = "Test"
    println("${when (null) { else -> x = null } }")
    x::inv // x is {String & Nothing}
    println(1) // unreachable code, but is executed
}
