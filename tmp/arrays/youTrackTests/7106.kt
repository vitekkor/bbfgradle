// Original bug: KT-28817

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

val lock = ReentrantLock()

val computed: Int = lock.withLock {
    while(true){
        return@withLock 3
    }
    throw IllegalStateException("unreachable")
}
