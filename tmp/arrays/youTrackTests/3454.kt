// Original bug: KT-38918

class Scratch {
    companion object {
        private const val NAME = "Test"

        fun myTestFunction() {}
    }

    fun sayMyName(): String {
        return NAME
    }
}

