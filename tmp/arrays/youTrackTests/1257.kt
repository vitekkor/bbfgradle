// Original bug: KT-34486

import java.nio.ByteBuffer

class Test {
    var state = 500
    fun callIntoJavaStdlib() {
        ByteBuffer.allocate(1).get(0)
    }
}

fun main() {
    Test().callIntoJavaStdlib()
}
