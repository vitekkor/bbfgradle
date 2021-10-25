// Original bug: KT-11589

fun main(args: Array<String>) {
    val a = (fun() {
        val a = {}
        println("Hello, world!")
    })
    (fun() {
        println("Hello, world!")
    })
    ({
        val a = {}
        println("Hello, world!")
    })
    println("Hello, world!")
}
