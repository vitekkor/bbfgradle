// Original bug: KT-35607

fun checkXInRange(x: Int, validNumbers: IntRange = 1..10) {
    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
}
