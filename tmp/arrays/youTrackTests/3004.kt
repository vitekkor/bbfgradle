// Original bug: KT-31365

import java.io.IOException

class Test {

    @Throws(IOException::class)
    fun throwException() {
        throw IOException("just a test exception!")
    }
}
