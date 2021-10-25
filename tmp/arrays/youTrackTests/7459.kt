// Original bug: KT-26508

inline class S(val x: String) {
    private fun foo() = x

    fun lambda() = { foo() }
}

fun main(args: Array<String>) {
    println(S("OK").lambda()())
}
