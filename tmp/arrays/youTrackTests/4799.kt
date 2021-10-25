// Original bug: KT-35434

import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    val pool = Executors.newSingleThreadExecutor()
    val function: () -> Int = { Thread.sleep(1000); 10 }
    val submit: Future<Int> = pool.submit(function)
}
