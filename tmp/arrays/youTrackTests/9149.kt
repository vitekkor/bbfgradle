// Original bug: KT-16127

public fun CharSequence.replaceRange(startIndex: Int, endIndex: Int, replacement: CharSequence): CharSequence {
    if (endIndex < startIndex)
        throw IndexOutOfBoundsException("End index ($endIndex) is less than start index ($startIndex).")
    val sb = StringBuilder()
    sb.append(this, 0, startIndex)
    sb.append(replacement)
    sb.append(this, endIndex, length)
    return sb
}
