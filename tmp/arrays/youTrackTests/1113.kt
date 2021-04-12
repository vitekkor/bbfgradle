// Original bug: KT-17554

fun main(args: Array<String>) {
    @Suppress("NON_EXHAUSTIVE_WHEN")
    when (0) {
        0 -> {
            if (true) {
                emptyArray<Any>().clone()
            }
        }
    }
}
