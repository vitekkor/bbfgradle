// Original bug: KT-9578

fun main(s: Any) {
    val x = when (s) {
        is String -> s
        is Int -> "$s"
        else -> return
    }

    val y: String = x // x has type Any resulting in an error
}
