// Original bug: KT-10616

package foo

fun main(args: Array<String>) {
    val xs = listOf(1, 2, 3).flatMap { x ->
        listOf(3, 4, 5).map { y ->
            object {
                val value = x + y
            }
        }
    }

    xs.forEach {
        println("value: " + it.value)
    }
}
