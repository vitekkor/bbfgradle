// Original bug: KT-15575

fun main(args: Array<String>) {
    transform(Array(1) { BooleanArray(1) }, 0)
}

fun transform(screen: Array<BooleanArray>, xx: Int) = Array(1) { x ->
    val column = screen[x]
    if (x == xx) {
        BooleanArray(1) { y -> column[Math.floorMod(y - 1, 1)] }
    } else {
        column
    }
}
