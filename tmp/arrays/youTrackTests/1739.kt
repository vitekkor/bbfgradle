// Original bug: KT-12562

import java.util.concurrent.*

class AL : ThreadPoolExecutor(0, 0, 0, null, null) {
    fun foo(r: () -> Unit) {
        this.execute(r)
        super.execute(r) // ERROR: Expected Runnable!, but found () -> Unit
    }
}
