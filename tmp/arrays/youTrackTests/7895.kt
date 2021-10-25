// Original bug: KT-24215

fun simple(param: Int = 0, init: () -> Unit): Int {
    init()
    return param
}

@Deprecated("Use useSimple instead", ReplaceWith("useSimple(simple(init))"))
fun use(init: () -> Unit): Int {
    init()
    return 0
}

fun useSimple(s: Int): Int {
    return s
}

fun useIt() {
    val t = use {
        println("abc")
    }
}
