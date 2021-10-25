// Original bug: KT-19600

class TestNumberConversionInSetter {
    private var d: Double = 0.toDouble()

    fun init() {
        setD(1.0) // Ok
    }

    fun setD(d: Double) {
        this.d = d
    }
}
