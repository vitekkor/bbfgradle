// Original bug: KT-30360

fun increaseIntensity(greyscales: ByteArray, factor: Double) {
    for (i in greyscales.indices) {
        greyscales[i] = (greyscales[i] * factor).toByte()
    }
}
