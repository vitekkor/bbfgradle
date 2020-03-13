//File Main.kt


fun foo(arr: Array<Color>): Color {
    loop@ for (color in arr) {
        when (color) {
            Color.RED -> return color
            Color.GREEN -> break@loop
            Color.BLUE -> if (arr.size == 1) return color else continue@loop
        }
    }
    return Color.GREEN
}

fun box() = if (foo(arrayOf(Color.BLUE, Color.GREEN)) == Color.GREEN) "OK" else "FAIL"



//File Color.java
import kotlin.Metadata;

public enum Color {
   RED,
   GREEN,
   BLUE;
}
