// Original bug: KT-38178

fun main() {
    val a = arrayOf(0, 1, 2, 3, 4, 5)
    val b = arrayOf(0, 1, 2, 3, 4, 5)
     a.forEach iteration@ { va ->
        var sum = va
        b.forEach { vb ->
            if (vb == 2) return@iteration
            sum += vb
            println("sum $sum")
        }
    }
    println("Hello, world!")
}

