// Original bug: KT-18819

fun eval(x: Int, message: String): Int {
    println(message)
    return x
}

fun main(args: Array<String>) {
    if (0 in eval(1, "low-1") .. eval(100, "high-1"))
        println("yes")
    else
        println("no")

    if ((eval(1, "low-2") .. eval(100, "high-2")).contains(0))
        println("yes")
    else
        println("no")

    if (0 >= eval(1, "low-3") && 0 <= eval(100, "high-3"))
        println("yes")
    else
        println("no")
}
