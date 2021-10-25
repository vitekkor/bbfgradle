// Original bug: KT-15609

private fun doSomething() {}

fun main(args: Array<String>) {
    try {                //  LINENUMBER 4
        doSomething()    //  LINENUMBER 5
    } catch (e: Throwable) {
        throw e
    }
}
