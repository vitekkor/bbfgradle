// Original bug: KT-30762

class FlowContextTest {
    fun repro() = sequence<Int> {
        inner()
    }

    private fun inner() {
        val inner = sequence<Double> {
            yield(1.0)
        }
    }
}
