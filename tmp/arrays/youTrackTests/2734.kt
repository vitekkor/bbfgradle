// Original bug: KT-13287

interface Category<in V> {
    fun contains(value: V): Boolean
}

fun categoryOf(range: IntRange) = object : Category<Int> {
    override fun contains(value: Int): Boolean = range.contains(value)
}

fun main(args: Array<String>) {
    val c = categoryOf(1..100) as Category<Any>
    println(c.contains(1L))    // true
    println(c.contains(1.0))   // true
    println(c.contains(1.0f))  // true
}
