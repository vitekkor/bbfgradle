// Original bug: KT-25350

fun main(vararg args: String) {

    val ids: List<String> = listOf("foo", "bar")
    val args1 = ids.toTypedArray()
    Thread {
        processIds(*args1)
    }.run()
    args1[0] = "messin with your data"
}

fun processIds(vararg args: String) {
    assert(args[0] == "foo")
}
