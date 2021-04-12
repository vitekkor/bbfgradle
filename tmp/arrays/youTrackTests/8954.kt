// Original bug: KT-17628

class Wat {
    val wat: Long
    init {
        wat = getValue()
    }

    private val someConstant = 1234L
    private fun getValue(): Long {
        return someConstant
    }
}

fun test(): Long {
    return Wat().wat
}

//the test() function returns 0 instead of 1234
