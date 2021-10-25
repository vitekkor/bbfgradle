// Original bug: KT-30054

interface C { val x: Int? }

fun <K> select(vararg x: K): K = x[1]

val test = select(object : C { override val x: Int? = 10 }, null)

fun main() = println(test.x) // NPE
