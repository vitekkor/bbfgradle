// Original bug: KT-33708

fun foo(p: Any?): String {
    require(p is String) { "p is not string" }
    return p.filter { it != 'A' }
}
// Simple try-catch-finally
fun tryCatchFinally(param: Any?) {
    try {
        foo(param)
    }
    catch (e: IllegalArgumentException) {
        println(e.message)
        println(e.cause)
    }
    finally {
        println("Finally block")
    }
}
