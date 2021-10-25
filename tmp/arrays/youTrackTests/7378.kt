// Original bug: KT-27627

fun leftPad(value: String, length: Int? = null, char: Char? = null): String {
        val length = length ?: 4
        val char = char ?: ' '
        return value.padStart(length, char)
}
