// Original bug: KT-39960

inline fun <T : Any> T.buildEquals(
      other: Any?,
      areFieldsEqual: T.(T) -> Boolean
): Boolean {
    if (this === other) return true
    if (this.javaClass != other?.javaClass) return false

    @Suppress("UNCHECKED_CAST")
    return areFieldsEqual(other as T)
}
