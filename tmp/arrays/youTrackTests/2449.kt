// Original bug: KT-41826

fun foo() {
    var a: Int = 10
    var b = 20L
    var c = 1_000_000
    var list = listOf(1, 2, 3, 4, 5)
    list
        .takeWhile { it != 3 }
        .forEach { println(it) }
    println("000000000000")
}
