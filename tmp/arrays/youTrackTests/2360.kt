// Original bug: KT-21231

object J {
    @JvmStatic
    fun main(args: Array<String>) {
        var lastIndex = 5
        for (i in 0 until lastIndex) {
            lastIndex--
            println(lastIndex)
        }
    }
}
