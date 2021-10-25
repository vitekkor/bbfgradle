// Original bug: KT-25912

fun main(args: Array<String>) {
    val gg = object : Grouping<Int, Int> {
        override fun sourceIterator(): Iterator<Int> = listOf(1).iterator()
        override fun keyOf(element: Int): Int = element
    }
    suspend {
        for (e in gg.sourceIterator()) {
            val key = gg.keyOf(e)
        }
    }
}
