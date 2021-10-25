// Original bug: KT-35576

class Foo(val s: String) {
    fun bar(): String {
        return extracted()
    }

    private fun Foo.extracted(): String { // Receiver parameter is never used
        return s
    }
}
