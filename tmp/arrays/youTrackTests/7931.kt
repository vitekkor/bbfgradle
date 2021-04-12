// Original bug: KT-24451

fun main(args: Array<String>) {
    when (true) {
    /**
     * comment 1
     */
        true -> println("true")
    // comment 2
        else -> println("false")
    }
}
