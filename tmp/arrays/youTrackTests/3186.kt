// Original bug: KT-37535

package m1.samConvention

fun interface Action {
    fun run()
}
fun runActionCase6(vararg a: Action) {
    a.forEach {
        it.run()
    }
}
fun main() {
    runActionCase6(
        { println("Hello, Case6_0!") },
        { println("Hello, Case6_1!") },
        { println("Hello, Case6_2!") }
    )
}
