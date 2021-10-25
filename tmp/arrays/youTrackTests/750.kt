// Original bug: KT-42262

fun main() {
    for (i in 0..5) {
        when (i) {
            0 -> println("start")
            3 -> break
            5 -> println("end")
            else -> println(i)
        }
    }
}
