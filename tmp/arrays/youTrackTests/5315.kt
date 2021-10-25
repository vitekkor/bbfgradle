// Original bug: KT-31999

fun test(str: String?): String? {
    val some = str?.substring(0..1) ?: return null // `some` is highlighted
    return when (some) {
        "some" -> some
        else -> ""
    }
}
