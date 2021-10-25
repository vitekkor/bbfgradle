// Original bug: KT-35788

fun main() {
    checkXInRange(42)
}

fun checkXInRange(x: Int, validNumbers: IntRange = 1..10) {
    println("x: $x, valid numbers: $validNumbers") // breakpoint
}
