// Original bug: KT-33391

fun funWithLocalFunctions() {
    // breakpoint 1
    val x = 1

    // breakpoint 2
    fun localFunctionExpression() = "Local function"

    // breakpoint 3
    val y = 2

    // breakpoint 4
    fun localFunctionBlock() {
        print(x + y)
    }
}
