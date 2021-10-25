// Original bug: KT-7052

import java.util.concurrent.Executors

fun main(args: Array<String>) {
    val executorService = Executors.newCachedThreadPool()
    executorService.submit { "foo" } // calls submit(Runnable task)
    executorService.submit({ "foo" } as () -> String) // calls submit(Callable<T> task)
}
