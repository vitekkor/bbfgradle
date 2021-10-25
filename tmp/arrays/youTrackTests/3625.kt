// Original bug: KT-28929

fun main(args: Array<String>) {
    val column = args[0].toInt() // breakpoint here
    val count = args[1].toInt()

    val x = column + count
    println(x)
}
