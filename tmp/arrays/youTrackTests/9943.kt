// Original bug: KT-10706

fun fn(c: Char?): Any? = if (c == null) TODO()
else when (c) {
    'a' -> when (c) {
        'B' -> listOf<String>()
        'C' -> "sdf"
        else -> TODO()
    }
    else -> TODO()
}
