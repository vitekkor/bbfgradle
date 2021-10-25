// Original bug: KT-31365

import java.io.IOException

class Test {
    @JvmField val jvmField: String = "" // Unresolved.

    @Throws(IOException::class) // Unresolved.
    fun throwException() {
        throw IOException("just a test exception!")
    }

    fun retest(s: String, ml: MutableList<String>) {} // No problem.

    @Deprecated("") fun predicated() {} // No problem.
}
