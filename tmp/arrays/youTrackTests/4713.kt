// Original bug: KT-32250

interface B {
    fun c()
}

class A(foo: Pair<Int, (B) -> Unit>? = null)

fun main() {
    val predicate = false
    val b = true
    val a = A(
        if (b) {
            1 to { baz -> baz.c() }
        } else {
            null
        }
    )
}
