// Original bug: KT-28428

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main() {
    var i: Future<Int> = Executors.newCachedThreadPool().submit(Callable<Int> {
        while (true) {
            try {
                return@Callable 42
            } catch (e: Exception) {
                throw e
            }
        }
        return@Callable null
    })
}
