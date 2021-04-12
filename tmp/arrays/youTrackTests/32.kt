// Original bug: KT-45865

class Test {
    fun thisWorks() {
        val list = listOf("foo", "bar")
        val item = list[0]
        enumValueOf<Page>(item)
    }

    fun thisBreaks() {
        val list = listOf("foo", "bar")
        enumValueOf<Page>(list[0])
    }
}

fun main() {
    Test().thisWorks()
    Test().thisBreaks()
}

enum class Page {
    Some,
    Values
}
