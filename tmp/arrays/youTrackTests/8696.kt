// Original bug: KT-19538

val b = "b".toUpperCase()
val a = init()

fun init(): String {
    println("b in init() = $b")
    return "a"
}

fun main(args: Array<String>) {
    println("b in main() = $b")
}
