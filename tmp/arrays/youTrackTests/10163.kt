// Original bug: KT-9023

fun main(args: Array<String>) {
    println("1")
    while (true) {
        if (true || break) {
            println("2")
            break
        }
    }
    println("3")
}
