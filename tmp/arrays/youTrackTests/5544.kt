// Original bug: KT-24516

import java.io.IOException

class Test {
    @Throws(IOException::class)
    fun readFile(): String {
        return "ok"
    }
}

fun main() {
    val test = Test()
    test.readFile()   // warning
}
