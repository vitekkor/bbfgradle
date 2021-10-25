// Original bug: KT-27248

data class Box(var s: String)

fun main(args: Array<String>) {
    val a = mutableSetOf(Box(s = "3"))
    val b = mutableSetOf(Box(s = "1"))
    a.forEach { r -> if (r.s == "3") r.s = "1" }

    println(a.javaClass) // class java.util.LinkedHashSet
    println(a.contains(Box(s = "1"))) // false
    println(b.contains(Box(s = "1"))) // true
    println(a == b) // false
}
