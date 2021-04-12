// Original bug: KT-26053

object ToBeInitialized {
    init {
        println("Initialized!")
    }
}

object InitBlockTest {
    @JvmStatic
    fun main(args: Array<String>) {
    }
}
