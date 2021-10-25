// Original bug: KT-36299

fun main() {
    val x: List<String> = listOf(1, 2).map {
        val z = getX()
        if (z != null) {
            return@map z
        }
        return@map ""
    }
}

fun getX(): String? {
    return null
}
