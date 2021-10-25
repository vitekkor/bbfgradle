// Original bug: KT-30617

class Structure() {
    infix fun createPyramid(rows: Int) {
        var k = 0
        for (i in 1..rows) {
            k = 0
            for (space in 1..rows - i) {
                print("   ")
            }
            while (k != 2 * i - 1) {
                print("* ")
            }
            println()
        }
    }
}

fun main() {
    val p = Structure()
    p createPyramid 4
}
