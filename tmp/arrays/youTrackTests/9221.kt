// Original bug: KT-15112

import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

private val evalStateLock = ReentrantReadWriteLock()
private val classLoaderLock = ReentrantReadWriteLock()
val compiledClasses = arrayListOf("")

fun eval(): String = evalStateLock.write {


    classLoaderLock.read {
        classLoaderLock.write {
            "write"
        }

        compiledClasses.forEach {
            it
        }
    }

    classLoaderLock.read {
            compiledClasses.map { it}
    }

    "default"
}
