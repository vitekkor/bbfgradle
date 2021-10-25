// Original bug: KT-30449

public fun String.removePrefix(prefix: CharSequence, ignoreCase: Boolean = false): String {
    if (startsWith(prefix, ignoreCase)) {
        return substring(prefix.length)
    }
    return this
}
