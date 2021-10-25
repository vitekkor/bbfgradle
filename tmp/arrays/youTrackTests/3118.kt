// Original bug: KT-20503

import kotlin.concurrent.thread

class VerifierCrash {
    init {
        runOnThread {
            builder { suspending() }
        }
    }

    fun builder(block: suspend () -> Unit) {} /* body does not matter */
    suspend fun suspending() {} /* body does not matter */

    inline fun runOnThread(crossinline action: () -> Unit) {
        /* double cross-inline */
        thread { action() }
    }

}

fun main(args: Array<String>) {
    VerifierCrash()
}
