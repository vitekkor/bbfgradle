// Original bug: KT-19027

fun test1(a: Any?) {
    val n: Any? = null
    if (a == n) {
        println("null")
    }
}

fun test2(a: Any?) {
    if (a == null) {
        println("null")
    }
}
