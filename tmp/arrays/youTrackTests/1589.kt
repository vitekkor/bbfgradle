// Original bug: KT-30402

val unsigned = 0x8fffffffU
val good = "123 " + unsigned
val bad = "123 " + 0x8fffffffU

fun main() {
    println(good) //123 2415919103
    println(bad) //123 -1879048193
}
