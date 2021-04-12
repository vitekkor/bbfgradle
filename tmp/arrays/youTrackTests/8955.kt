// Original bug: KT-17628

class Wat2 {
    val wat: Long
    private val someConstant = 1234L
    init {
        wat = getValue()
    }

    private fun getValue(): Long {
        return someConstant
    }
}
