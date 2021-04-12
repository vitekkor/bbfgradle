// Original bug: KT-33726

fun doSmth(): Int {
    return 10
}

fun tryAsExpression() {
    val a: Int? = try { //breakpoint 1
        doSmth() // breakpoint 2
    } catch(e: NullPointerException) {
        0
    }
}

fun main() {
    tryAsExpression()
}
