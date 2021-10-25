// Original bug: KT-31276

inline class A(val id: Int) {
    fun generate() {
        foo()
    }

    companion object {
        private val bar = ""
        private inline fun foo() {
            bar
        }
    }
}
