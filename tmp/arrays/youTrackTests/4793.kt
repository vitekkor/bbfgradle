// Original bug: KT-10192

fun foo() {
    val list: MutableList<String>? = arrayListOf("")
    if (true) {
        when (true) { // NO_ELSE_IN_WHEN
            true -> println()
        }
    } else {
        list?.add("hello") // Boolean?
    }
}