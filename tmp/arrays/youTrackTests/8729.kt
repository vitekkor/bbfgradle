// Original bug: KT-19246

enum class ErrorType constructor(val reason: String) {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    UNKNOWN("3");

    companion object {
        fun getByVal(reason: String): ErrorType {
            return ErrorType.values().firstOrDefault({ (it.reason == reason) }, UNKNOWN)
        }
    }
}

//array inline extension function
inline fun <T> Array<out T>.firstOrDefault(predicate: (T) -> Boolean, default: T): T {
    return firstOrNull(predicate) ?: default
}


fun main(args: Array<String>) {
    val errorType = ErrorType.ONE
    when (errorType) {
        ErrorType.ONE -> {
            println("ONE")
        }
        else -> {
            println("nothing")
        }
    }
}


