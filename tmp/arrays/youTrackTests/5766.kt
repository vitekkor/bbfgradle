// Original bug: KT-30976

fun String.foo() {
    val try1 = this // I can evaluate 'this' with the debugger stopped here
    val try2 = Pair(
        this, // I can evaluate 'this' with the debugger stopped here
        this.length
    )
    val try3 = Pair(
        first = this, // I cannot evaluate 'this' with the debugger stopped here
        second = this.length
    )
}
