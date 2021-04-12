// Original bug: KT-21365

fun f(): Int {
    return throw return throw return throw return 1
}

fun g(): Int {
    return throw return throw return throw Exception()
}

fun main(args: Array<String>) {
    println(f()) // Prints 1 on console
    g() // Throws Exception
}
