// Original bug: KT-38895

fun takeByte(x: Byte) {}
fun take(x: Int) {} // (1)
fun take(x: Long) {} //2

fun test() {
    takeByte(1 + 1) // will be an error
    take(2147483648 - 1 + 1) // now resolved to (1), will be resolved to (2)
}
