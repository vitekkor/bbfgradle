// Original bug: KT-14460

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

val lock = ReentrantLock()
val map = mapOf<Int, String>()

fun foo(): String =
        map[0] ?:
                lock.withLock { // ko
             // map.getOrElse(0) { // ok
            var res = map[0]
            if (res == null) {
                res = ""
            }
            res!! // unnecessary cast is required using lock variable
        }
