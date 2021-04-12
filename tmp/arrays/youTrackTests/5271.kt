// Original bug: KT-32651

fun test() {
    when {
        true -> {
            // comment
            // comment
            println(true)
        }
        false -> println(false)
        else -> TODO()
    }
}
