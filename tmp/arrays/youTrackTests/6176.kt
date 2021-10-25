// Original bug: KT-30984

fun main() {
    val x: Boolean? = null
    outer@ while (true) {
        while (x ?: break@outer) {
            println(1)
        }
    }
    println(2) // is printed
}
