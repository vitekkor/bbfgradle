// Original bug: KT-23680

fun main(args: Array<String>) {
    println(
            executeProof()
    )
}

fun executeProof(): String {
    var result = "not executed"

    try {
        throw RuntimeException("EXECUTED!")
    } catch (e: Exception) {
        result = e.message ?: "{null message}"
    } finally {
        return result
    }
}
