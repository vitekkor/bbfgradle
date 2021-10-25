// Original bug: KT-21898

import java.util.concurrent.*

fun y(q: LinkedBlockingQueue<String>) {
    try {
        val x = q.poll(1, TimeUnit.SECONDS)
        println(x)
    } catch (e: InterruptedException) {
        return
    }
}

fun x(q: LinkedBlockingQueue<String>) {
    val x: String // Not nullable
    try {
        // Static code check shows nothing
        // poll could return null
        // Hangs on kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull()
        x = q.poll(1, TimeUnit.SECONDS)
    } catch (e: InterruptedException) {
        return
    }
    println(x)
}

fun main(args: Array<String>) {
    val queue = LinkedBlockingQueue<String>()
    val tpe = Executors.newSingleThreadScheduledExecutor()

    tpe.scheduleWithFixedDelay({ x(queue) }, 0, 1, TimeUnit.SECONDS)
    y(queue)
    x(queue)
}
