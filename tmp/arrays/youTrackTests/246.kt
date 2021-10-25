// Original bug: KT-44878

fun main() {
    val first: Int? = 1
    val second: Int? = 2
    val third = 3

    val result = when {
        first == null || second == null -> 0
        third in first..second -> 1
        else -> 2
    }

    println(result)
}
