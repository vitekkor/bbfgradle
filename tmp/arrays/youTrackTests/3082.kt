// Original bug: KT-19548

class TestCastAndCheckForNull {
    fun foo(): String {
        return bar() as String ?: return "null"
    }

    fun bar(): Any {
        return "abc"
    }
}
