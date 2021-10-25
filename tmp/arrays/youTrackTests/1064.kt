// Original bug: KT-44674

fun main() {
    val lambdas = mutableListOf<() -> Int>()
    val n = 1_000_000
    for (i in 0 until n) {
        val lambda = { i + 1 }
        lambdas += lambda
    }
    check(lambdas.size == n)

    println("ready to capture memory snapshot")
    Thread.sleep(3_600_000)
}
