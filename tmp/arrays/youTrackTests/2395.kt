// Original bug: KT-27488

fun functionWithSideEffects(): Boolean {
    println("Side effect")
    return true
}

fun main(args: Array<String>) {
    var x = functionWithSideEffects()
    x = false
    println(x)
}
