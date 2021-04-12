// Original bug: KT-40867

fun printIntAndGet(i: Int) = i.also(::println)

fun main() {
    val i1 = printIntAndGet(1)
    println(i1)
    printIntAndGet(i1)
    println(printIntAndGet(2))
}
