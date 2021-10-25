// Original bug: KT-13970

fun test(cond1: Boolean) {
    var cond: Boolean
    @Suppress("UNINITIALIZED_VARIABLE")
    do {
        if (cond1) continue
        cond = false
    } while (cond) //Error: Variable 'cond' must be initialized
}

fun main(args: Array<String>) {
    test(true)
}
