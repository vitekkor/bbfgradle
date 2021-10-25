// Original bug: KT-38764

data class Color(
    val red: Float,
    val green: Float,
    val blue: Float
) {
  companion object {
    val BLACK: Color = Color(0, 0, 0)
  }
}

fun Color(red: Number, green: Number, blue: Number): Color {
  if (red == 0 && green == 0 && blue == 0) {
    return Color.BLACK
  }

  return Color(red.toFloat(), green.toFloat(), blue.toFloat())
}


fun main() {
  val color: Color = Color(0, 0, 0)

  println(color) // prints null
  println(color.green) // throws NullPointerException
}
