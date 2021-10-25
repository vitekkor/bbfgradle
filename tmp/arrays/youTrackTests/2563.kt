// Original bug: KT-18618

fun bar() {
    fun foo(x: Int): Boolean {
        if (x == 42) {
            return true
        }
        return x < -42
    }

    if (foo(42)) {
        print("!")
    }
}


fun test() {
    bar() // Inline this
}
