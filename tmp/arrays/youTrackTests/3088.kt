// Original bug: KT-35883

fun test() {
    test2 {
        var example: CharSequence
        example = "my string"

        try {
            throw Throwable()
        } catch (throwable: Throwable) {
            example = StringBuilder()
        }
        example
    }
}
fun <T> test2(block: () -> T) {
}
