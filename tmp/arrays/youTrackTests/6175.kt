// Original bug: KT-30984

fun main() {
    val x: Boolean? = null
    while (true) {
        while (x ?: break) {
            println(1)
        }
    }
    println(2) // never printed
}
