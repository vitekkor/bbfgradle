// Original bug: KT-15646

fun main(args: Array<String>) {
    try {
        throw Exception() // set breakpoint here and hit Step Over
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
