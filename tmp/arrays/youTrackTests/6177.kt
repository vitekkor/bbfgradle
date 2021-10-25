// Original bug: KT-30984

fun main() {
    val x: Boolean? = null
    do {
        do {
            println(1) // will be printed an infinite number of times
        } while (x ?: break) // break is affected inner loop only
    } while (true)
    println(2)
}
