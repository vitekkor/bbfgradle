// Original bug: KT-13969

fun test(cond1: Boolean) {
    do {
        var cond = false
        if (cond1) continue
        cond = true
    } while (cond)
}
fun main(args: Array<String>) {
    test(true)
}
