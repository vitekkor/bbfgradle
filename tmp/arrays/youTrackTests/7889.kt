// Original bug: KT-22096

class Foo {
    inline fun bar() {
        listOf("").forEach {
            it.baz()
        }
    }

    // false positive here
    fun String.baz() {}
}
