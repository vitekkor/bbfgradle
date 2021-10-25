// Original bug: KT-17685

fun main(args: Array<String>) {
    var stage = ""
    try {
        stage = "computation"
        1 / 0
        stage = "verification"
    } catch (e: Exception) {
        System.err.println("$stage failed: $e")
    }
}
