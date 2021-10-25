// Original bug: KT-30473

enum class Numbers {
    ONE,
    TWO,
    THREE
}

fun testNumbersWhen(n: Numbers): Int {
    return when(n) {
        Numbers.ONE -> 1
        Numbers.TWO -> 2
        Numbers.THREE -> 3
    }
}
