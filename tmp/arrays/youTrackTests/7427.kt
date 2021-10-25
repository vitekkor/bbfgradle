// Original bug: KT-27476

fun main(args: Array<String>) {
    var errorMsg = "0"
    try {
        errorMsg = "1"
        someFunc()
        errorMsg = "2"
    } catch (e: Exception) {
        println(errorMsg)
        e.printStackTrace()
    }
}

private fun someFunc() {
    throw RuntimeException()
}
