// Original bug: KT-35779

fun main() {
    val top = 144
    with(Some()) {
        val list = mutableListOf(100, 200, 300, 20, 30, 10, 50)
        print("sorted list=$list") // breakpoint
    }
}

class Some
