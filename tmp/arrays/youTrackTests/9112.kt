// Original bug: KT-15392

suspend fun qwe() {
    // suspend fun localHelper(x: Int) {}             // Syntax error
    // val localHelper = suspend fun(x: Int) {}       // Syntax error
    val localHelper: suspend (x: Int) -> Unit = {}    // Works

    localHelper(10)
    localHelper(20)
    localHelper(30)
}
