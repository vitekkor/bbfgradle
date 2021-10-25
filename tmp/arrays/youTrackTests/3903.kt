// Original bug: KT-18282

open class Base(val me: String) {
    companion object : Base(string()) { // <- should be flagged as an error!
        private fun string() = "companion object"
    }
}

fun main() {
    Base("") // Exception in thread "main" java.lang.VerifyError: Bad type on operand stack
}
