// Original bug: KT-33408

fun getStatus() = "SUCCESS"

fun main() {
    when (val response = getStatus()) {
        "SUCCESS" -> response.plus("12345")
        else -> throw NullPointerException(response)
    // breakpoint here
    }
}
