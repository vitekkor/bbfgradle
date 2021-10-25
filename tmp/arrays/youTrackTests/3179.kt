// Original bug: KT-39313

fun main() {
    val list = listOf(1, 2)
    list.inc() // extract method on this line
}

private fun List<Int>.inc() { // receiver on the extracted method
    map { it + 1 }
}
