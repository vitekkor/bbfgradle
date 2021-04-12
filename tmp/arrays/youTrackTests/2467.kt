// Original bug: KT-9992

fun main() {
    val foo: List<List<Int>> = foo()
    val bar: List<Int> = bar()
    println(foo + bar)            // [[1, 2], [3, 4], 5, 6] - unexpected
    println(foo.plus(bar))        // [[1, 2], [3, 4], 5, 6] - unexpected
    println(foo.plusElement(bar)) // [[1, 2], [3, 4], [5, 6]] - OK
}

fun foo(): List<List<Int>> = listOf(
    listOf(1, 2),
    listOf(3, 4)
)

fun bar(): List<Int> = listOf(5, 6)
