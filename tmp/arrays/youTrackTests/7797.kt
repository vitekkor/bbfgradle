// Original bug: KT-18681

fun test(a: String): Int {
    when(a) {
        "" -> {
            when (a) {
                "" -> return 1
                else -> {
                }
            }
        }
    }
    return 2
}
