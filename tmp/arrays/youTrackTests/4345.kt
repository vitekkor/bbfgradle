// Original bug: KT-20868

class Some {
    fun bar() {
        big.foo()
    }

    companion object {
        private val big = object : Any() {
            fun foo() {} // unused
        }
    }
}
