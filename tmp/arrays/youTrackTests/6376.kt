// Original bug: KT-13396

/**
 * @return a pair of the part before the index and the rest after the index (including the character at index)
 */
inline fun CharSequence.partitionAt(index: Int): Pair<String, String> {
    return substring(0, index) to substring(index)
}

// or a bit more sophisticated:

/**
 * @param skipNumAfterIndex the number of characters to skip after the index
 *
 * @return a pair of the part before the index and the rest after the index (including the character at index)
 */
inline fun CharSequence.partitionAt(index: Int, skipNumAfterIndex: Int = 0):
        Pair<String, String> {
    return substring(0, index) to substring(index + skipNumAfterIndex)
}
