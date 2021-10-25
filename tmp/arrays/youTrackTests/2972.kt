// Original bug: KT-39753

/**
 * Parses [this] string as an [Int] number and returns the result      <-- Warning here on [this]
 * or `null` if the string is not a valid representation of a number.
 */
val String.asIntOrNull: Int?
    get() = 5
