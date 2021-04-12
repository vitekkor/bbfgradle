// Original bug: KT-33694

object Operators {
    @JvmStatic
    fun main(args: Array<String>) {
        val str1 = "Test"
        println( //four-lines statement
            "String:" +
                    (str1 + "1")
        )
    }
}
