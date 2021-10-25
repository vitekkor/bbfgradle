// Original bug: KT-8970

fun main() {
    println(Color.Blue)
    println(Color.allColors)
}

sealed class Color {
    object Red : Color();
    object Blue : Color();

    companion object {
        val allColors = listOf(
                Red,
                Blue
        )
    }
}
