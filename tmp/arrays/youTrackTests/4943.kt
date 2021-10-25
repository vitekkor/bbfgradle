// Original bug: KT-29691

fun main(args: Array<String>) {
    val posZero = 0.0
    val negZero = -0.0
    if (posZero == negZero)
        println("EQUAL")
    else
        println("NOT EQUAL")
}
