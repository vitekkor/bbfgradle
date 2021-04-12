// Original bug: KT-34093

class Region(
    val width: Int,
    val height: Int
) {
    fun contains1(x: Int, y: Int) =
        x in 0 until width && y in 0 until height

    fun contains2(x: Int, y: Int) =
        x >= 0 && x < width && y >= 0 && y < height
}
