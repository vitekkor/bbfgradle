// Original bug: KT-27615

fun f1(): List<Result<Int>> {
    return listOf(
        runCatching {
            println("1")
            10
        }
    )
}

fun main() {
    val t0 = f1()
    val t1 = f1()[0]

    val t2 = runCatching {
        println("1")
        10
    }

    println(t0) // [Success(10)]
    println(t1) // Success(Success(10))
    println(t2) // Success(10)
}
