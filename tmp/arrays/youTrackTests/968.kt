// Original bug: KT-45433

package test

fun main() {
    time("var") {
        val y = when {
            else -> {
                val arr = IntArray(100000000) { it }
                var s = 0L
                for (i in arr) s += i
                s
            }
        }
        test(10, y)
    }
    time("arg") {
        test(
            10,
            when {
                else -> {
                    val arr = IntArray(100000000) { it }
                    var s = 0L
                    for (i in arr) s += i
                    s
                }
            }
        )
    }
}

fun test(x: Int, y: Long) {
    println("x=$x, y=$y")
}

fun time(caption: String, block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()
    println("$caption: ${end - start}")
}
