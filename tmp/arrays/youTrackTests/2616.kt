// Original bug: KT-31438

fun <T1, T2, R> List<T1>.combine(other: List<T2>, transform: (T1, T2) -> R): R =
    (this as List<*>).combine(*arrayOf(other)) { TODO() }

fun <R> List<*>.combine(vararg others: List<*>, transform: (Array<Any?>) -> R): R = TODO()
