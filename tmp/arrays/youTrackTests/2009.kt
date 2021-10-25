// Original bug: KT-43246

class A(var x: Int = DEFAULT_X) {
    companion object {
        val DEFAULT = A()
        private val DEFAULT_X = 32768
    }
}
fun main() {
    println(A.DEFAULT.x) // <-- GOT "0"
}
