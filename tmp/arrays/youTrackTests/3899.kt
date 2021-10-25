// Original bug: KT-37861

package test

class Outer(val x: Any) {
    inner class Inner(
        val fn: () -> String = { x.toString() }
    )
}

fun main() {
    val t = Outer("OK").Inner()
    println(t.fn())
}
