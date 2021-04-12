// Original bug: KT-22899

class Test {
    fun foo() {
        val b = bar()
        requireNotNull(b)
        b.length //PROBLEM - b is still nullable

        if (b == null) throw RuntimeException()
        b.length //OK - b is non nullable
    }

    fun bar():String? {
        return ""
    }
}
