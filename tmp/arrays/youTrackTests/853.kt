// Original bug: KT-44972

@Suppress("conflicting_overloads", "nothing_to_inline")
@JvmName("hexStringToByteArrayUpperCase")
inline fun String.hexStringToByteArray(upperCase: Boolean): ByteArray {
    return hexStringToByteArray(lowerCase = upperCase.not())
}

@Suppress("conflicting_overloads")
@JvmName("hexStringToByteArrayLowerCase")
fun String.hexStringToByteArray(lowerCase: Boolean): ByteArray {
    TODO() // Implementation is irrelevant.
}
