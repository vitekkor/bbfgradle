// Original bug: KT-8754

interface I {
    fun f()
}

fun main(args: Array<String>) {
    foo("", emptyList())
}

fun foo(o: Any?, list: List<String>): I? {
    return o?.let {
        object: I {
            override fun f() {
                list.firstOrNull { it.isNotEmpty() }
            }
        }
    }
}
