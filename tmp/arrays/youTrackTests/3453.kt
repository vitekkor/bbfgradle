// Original bug: KT-38918

class Scratch {
    fun sayMyName(): String {
        return NAME
    }
    companion object {
        private const val NAME = "Test"
        fun myTestFunction() {}
    }
}
