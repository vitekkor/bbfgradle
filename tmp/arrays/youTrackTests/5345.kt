// Original bug: KT-26617

fun call() = 12
fun pair() = 12 to 12

infix fun <T, U> T.invert(b: U) = b to this

val pairs: List<Pair<Any, Int>> = listOf(
    1                         to 122,
    "ABCDEF"                  to 1,
    "Some very long sentence" to call(),
    pair(),
    123 invert "Inverted",
    1.0                       to 42
)
