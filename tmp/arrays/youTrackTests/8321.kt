// Original bug: KT-21898

import java.util.concurrent.*

class Queue {
    private val tpe: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private val queue: LinkedBlockingQueue<String> = LinkedBlockingQueue()

    init {
        tpe.scheduleWithFixedDelay({ this.poll() }, 0, 1, TimeUnit.MILLISECONDS)
    }

    private fun poll() {
        println("Poll")
        val message: String // Here should be String?
        try {
            message = queue.poll(1, TimeUnit.MILLISECONDS) // <- Stacktrace will be never printed
        // In case we'll put ISE everything will work
        //} catch (e: IllegalStateException) {
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return
        }
        println("Never comes here $message")
    }
}

fun main(args: Array<String>) {
    Queue()
    while (true) {
        Thread.sleep(1000)
    }
}
