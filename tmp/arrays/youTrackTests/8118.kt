// Original bug: KT-17110

fun main(args: Array<String>) {
    val x = 10
    val y = 9
    if(x == 6) {
        if(y == 6) {
            println("a")
        } else {
            println("b")
        }
    } else {
        println("c")
    }
}
