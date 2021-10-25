// Original bug: KT-9578

fun main(s: Any) {
    val x = when (s) {
        is String -> s as String // meaningless
        is Int -> "$s"
        else -> return
    }

    val y: String = x // no error
}
