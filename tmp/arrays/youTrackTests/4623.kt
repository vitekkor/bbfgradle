// Original bug: KT-19642

class TestMessedJavadoc {

    /**
     * javadoc 2
     * @param flag
     */
    @JvmOverloads
    fun foo(flag: Boolean = true) {
    }
}
/**
 * javadoc 1
 */
