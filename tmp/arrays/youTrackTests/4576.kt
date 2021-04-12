// Original bug: KT-31540

var counter = 0
fun inc() = counter++

tailrec fun test(x: Int = 0, y: Int = inc(), z: Int = inc()) {
    println("y: $y, z: $z")
    if (x > 0) test(x - 1)
}

fun main() {
    test(1)
}
