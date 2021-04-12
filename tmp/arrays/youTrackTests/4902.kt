// Original bug: KT-33860

fun withDefaultParameters(param1: Int = 0, param2: String = "ABC") {
    println(param1) // breakpoint here
    println(param2)
}

fun call() {
    withDefaultParameters()
}

fun main() {
    call()
}
