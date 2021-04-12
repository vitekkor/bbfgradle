// Original bug: KT-41493

class A {
    fun foo(): String {
        // Old backend: A$foo$result$2.class
        // JVM IR: A$foo$result$result$delegate$1.class
        val result by lazy { "OK" }
        return result
    }
}
