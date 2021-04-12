// Original bug: KT-44972

import kotlin.jvm.JvmName

fun ByteArray.toHex(lowercase: Boolean = false, separator: String = ""): String {
    if (size == 0) return ""
    val result = StringBuilder(size * 2 + separator.length * (size - 1))
    val hexChars = if (lowercase) hex_chars else HEX_CHARS
    val appendSeparator = separator.isNotEmpty()
    forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(hexChars[firstIndex])
        result.append(hexChars[secondIndex])
        if (appendSeparator) result.append(separator)
    }
    return if (appendSeparator) result.removeSuffix(separator).toString() else result.toString()
}

@Suppress("conflicting_overloads", "nothing_to_inline")
@JvmName("hexStringToByteArrayUpperCase")
inline fun String.hexStringToByteArray(upperCase: Boolean): ByteArray {
    return hexStringToByteArray(lowerCase = upperCase.not())
}

@Suppress("conflicting_overloads")
@JvmName("hexStringToByteArrayLowerCase")
fun String.hexStringToByteArray(lowerCase: Boolean): ByteArray {

    val hexChars = if (lowerCase) hex_chars else HEX_CHARS

    val result = ByteArray(length / 2)

    for (i in 0 until length step 2) {
        val firstIndex = hexChars.indexOf(this[i])
        val secondIndex = hexChars.indexOf(this[i + 1])
        result[i.shr(1)] = ((firstIndex shl 4) or secondIndex).toByte()
    }

    return result
}

@OptIn(ExperimentalStdlibApi::class)
private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

@OptIn(ExperimentalStdlibApi::class)
private val hex_chars = "0123456789abcdef".toCharArray()
