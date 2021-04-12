// Original bug: KT-14486

fun test(flag: Boolean) {
    var x: String? = null
    if (flag) x = "Yahoo!"

    run {
        if (x != null) {
            println(x.length)
            // Error:(9, 21): Smart cast to 'String' is impossible, because 'x' is a local variable that is captured by a changing closure
        }
    }
}
