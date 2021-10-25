// Original bug: KT-19811

class Foo {
    fun test() {
        abacaba("")
    }

    internal fun abacaba(s: String): String = s  // <-- wrong "unused member"
}

fun main(args: Array<String>) {
    Foo().test()
}
