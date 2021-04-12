// Original bug: KT-35775

class Test {
    val someValue: String
        get() = "A"

    fun evalValue1(): String {
        val someValue = "X"
        return someValue + someValue
    }

    fun evalValue2(): Int {
        val divider = 2.3f
        var value = 10000
        value /= divider.toInt()
        return value
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = Test()
            println(test.evalValue1())
            println(test.evalValue2())
        }
    }
}
