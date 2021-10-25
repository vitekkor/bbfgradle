// Original bug: KT-33850

class Foo() {
    private val s: String
        get() {
            val text = "Zap"
            return text
        }

    fun bar() {
        val text = s
        println("text = ${text}")
    }
}
