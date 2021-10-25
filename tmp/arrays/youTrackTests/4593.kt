// Original bug: KT-34404

fun main() {
    errorWhenSelectingProvider(true, Byte.MAX_VALUE, Char.MAX_VALUE, SimpleClass())
}

fun errorWhenSelectingProvider(bool: Boolean, byte: Byte, char: Char, simpleClass: SimpleClass) {
    println() //breakpoint here
}

class SimpleClass {
    val v1 = 1
    val str = "test"
}
