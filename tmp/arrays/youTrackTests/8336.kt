// Original bug: KT-20351

package debugger.kt20351

fun test(i: Int): Boolean {
    return false
}

fun main(args: Array<String>) {
    val testSome = listOf(1, 2, 3).filterIsInstance<Int>().filterNot(::test)
    println("More")
}


