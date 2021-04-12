// Original bug: KT-22094

object Test {
    @JvmStatic
    inline fun foo(f: () -> String) {
        run {
            bar(f())
        }
    }

    // false positive here
    fun bar(s: String) = s
}
