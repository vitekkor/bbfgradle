// Original bug: KT-30919

fun main() {
    var a = 1
    var b = 3
    var c = a + b
    println(c)
    listOf(1,2,3,4).map { i ->
        val d = i + c
        d - a
    }.let {
        println(it)
    }
}
