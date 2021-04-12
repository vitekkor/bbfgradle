// Original bug: KT-6005

import java.util.concurrent.Future
import java.util.concurrent.FutureTask

fun main(args: Array<String>) {
    val future: FutureTask<Int> = FutureTask() { 1 } // error
    val y = FutureTask() { 1 } // ok, but y has type FutureTask<V> (as within declaration of FutureTask<V>)
}
