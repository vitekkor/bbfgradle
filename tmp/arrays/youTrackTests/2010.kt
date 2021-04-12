// Original bug: KT-43246

class A(val x: String = DEFAULT_X) {
    companion object {
        val DEFAULT = A()
        private val DEFAULT_X = "HI"
    }
}
fun main() {
    println(A.DEFAULT.x)
}
