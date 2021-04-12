// Original bug: KT-26860

class SampleNotWorking {
    private val builder = StringBuilder()
    fun addMessage() {
        builder.append("hello")
    }

    override fun toString(): String {
        return builder.toString()
    }
}

class SampleWorking {
    private val builder = StringBuilder()
    fun addMessage() {
        builder.append("hello")
    }

    override fun toString() = builder.toString()
}
