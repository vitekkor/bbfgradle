// Original bug: KT-42132

// WITH_RUNTIME

class Something(val s: String = "")
class Some {
    fun test() = listOf("O", "K").map(Some::getSomething)

    companion object {
        fun getSomething(s: String): Something = Something(s)
    }
}

fun box(): String {
    return Some().test().joinToString(separator = "") { it.s }
}
