// Original bug: KT-33589

data class ABC(
    val a: Int,
    val b: Int,
    val c: Int,
    val d: Int,
    val e: Int
)

fun testing() = ABC(
    a = 1,
    b = 2,
    c = 3,
    d = 4,
    e = 5
)
