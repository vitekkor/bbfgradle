// Original bug: KT-14730

fun bytesToInt(b: ByteArray): Int {
    return (b[0].toInt() shl 24) or
        ((b[1].toInt() and 0xff) shl 16) or
        ((b[2].toInt() and 0xff) shl 8) or
        (b[3].toInt() and 0xff)
}
