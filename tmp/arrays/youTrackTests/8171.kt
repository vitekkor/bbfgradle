// Original bug: KT-21918

import java.util.concurrent.ConcurrentLinkedQueue

fun main(args: Array<String>) {
    val queue = ConcurrentLinkedQueue<String>()
    val thread = Thread({
        while (true) {
            for (i in 1..1000) queue.offer("foo")
            for (i in 1..1000) queue.poll();
        }
    })
    thread.isDaemon = true
    thread.start()
    for (i in 1..100) {
        val array1 : Array<String?> = queue.toArray(emptyArray())
        val nulls = array1.filter { it == null }.size
        if (nulls > 0) {
            println("toArray: ${nulls} nulls returned")
        }
    }
    for (i in 1..100) {
        val array2: Array<String?> = queue.toTypedArray()
        val nulls = array2.filter { it == null }.size
        if (nulls > 0) {
            println("toTypedArray: ${nulls} nulls returned")
        }
    }
}
