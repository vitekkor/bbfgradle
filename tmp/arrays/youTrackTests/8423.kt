// Original bug: KT-20435

@JvmName("intListPrint")
fun List<Int>.print() {
    println("integers: $size")
}

@JvmName("stringListPrint")
fun List<String>.print() {
    println("strings: $size")
}

fun main(args: Array<String>) {
    listOf(1, 2, 3).print()
    listOf("a", "b", "c").print()
}
