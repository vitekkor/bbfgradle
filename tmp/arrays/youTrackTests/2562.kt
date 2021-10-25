// Original bug: KT-18618

interface Some {
    fun foo(): Int
}

fun bar(): Some {
    val x = 42
    return object : Some {
        override fun foo(): Int {
            return x
        }
    }
}

fun test() {
    val some = bar() // Inline this
}
