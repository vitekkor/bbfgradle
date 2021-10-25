// Original bug: KT-14511

interface I {
    fun foo()
}

inline fun bar(crossinline block: () -> Unit) {
    object : I {
        override fun foo() {
            block()
        }
    }
}
