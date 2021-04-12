// Original bug: KT-16177

// kotlin 1.0.6
val FIRST_VALUE = function()
val SECOND_VALUE = FIRST_VALUE()

private val LAMBDA: () -> Unit = {}
private fun function(): () -> Unit = LAMBDA

fun main(args: Array<String>) {
    SECOND_VALUE
}
