// Original bug: KT-6646

fun main(args: Array<String>) {
    val cache: String

    when ("1") {
        "1" -> {
            cache = "1"
        }
        else -> {
            error("unspecified tab")
        }
    }
    val p = cache
}
