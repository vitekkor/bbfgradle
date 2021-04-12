// Original bug: KT-16713



class MyQueue {
    fun poll(): String? = "OK"
}

class A<T> {

    val delayedQueue = MyQueue()

    fun <T> next(): String {
        while (true) {
            delayedQueue.poll() ?: break
        }

        while (true) {
            unblock(delayedQueue.poll() ?: break)
        }

        while (true) {
            addToQueues(delayedQueue.poll() ?: break)
        }
        return "123"
    }

    fun addToQueues(p: String) {
    }

    fun unblock(p: String) {

    }
}