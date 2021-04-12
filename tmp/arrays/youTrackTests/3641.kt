// Original bug: KT-21783

fun main(args: Array<String>) {
    val test = returnList(42) // breakpoint
    test.forEach { println(it) } 
}

fun returnList(i: Int): List<String> { // breakpoint
    val res = mutableListOf<String>()
    val bound = if (i > 1) i else 2
    return (0..bound).mapTo(res) { "element $it" }
}
