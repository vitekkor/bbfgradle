// Original bug: KT-10436

fun <E : Enum<E>> enumSetOf(e1: E) = ""
fun <E : Enum<E>> enumSetOf(e1: E, e2: E? = null) = 1

enum class En { A, B, C }

val es = enumSetOf(En.A)
// Error:(17, 10) Kotlin: Cannot choose among the following candidates without completing type inference: 
// public fun <E : kotlin.Enum<kt.En>> enumSetOf(e1: kt.En): ...
// public fun <E : kotlin.Enum<kt.En>> enumSetOf(e1: kt.En, e2: kt.En? = ...): ...
