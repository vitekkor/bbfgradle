// Original bug: KT-35521

fun main() {
    p() // breakpoint 
}

fun p(
    bool: Boolean = false,
    byte: Byte = Byte.MIN_VALUE,
    char: Char = Char.MAX_VALUE
) {
    println()
}
