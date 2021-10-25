// Original bug: KT-22821

fun checkFloatArray(): Boolean {
    val floatArray = floatArrayOf(1.1f, 2.2f, 3.3f)
    var sum = 0f
    for (i in 0..floatArray.size - 1) {
        sum += floatArray[i]
    }
    if (sum != (1.1f + 2.2f + 3.3f)) return false
    return true
}
