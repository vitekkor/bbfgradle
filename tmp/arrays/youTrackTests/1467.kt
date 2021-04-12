// Original bug: KT-29252

fun main() {
    A("").foo()
}

class A(val prop: String) {
    fun foo() = Inner()

    inner class Inner {
        private val obj = 42.let {
            object {
                val p = prop
            }
        }
    }
}
