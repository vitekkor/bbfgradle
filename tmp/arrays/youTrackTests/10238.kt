// Original bug: KT-2851

fun main(args: Array<String>) {
    val value: String? = ""
    if (value != null) {
        foo(Pair("val", value))
        foo(Pair("val", value!!))
        foo(Pair<String, String>("val", value))
    }
}

fun foo(map: Pair<String, String>) {}
