// Original bug: KT-23415

class Demo {
    fun main() {
        val inlineMe = create()
        "test".apply {
            use(inlineMe)
        }
    }
    fun create() = "test"
    fun use(string: String) = Unit
}
