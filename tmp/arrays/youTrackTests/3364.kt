// Original bug: KT-38969

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val s = "abc,def"
        val parts = s.split(delimiters = charArrayOf(','))
        println(parts)
    }
}
