// Original bug: KT-20434

import java.lang.ref.WeakReference

fun main(args: Array<String>) {
    val list = mutableListOf<WeakReference<Any>>()
    
    for (i in 1..3) {
        val obj = Any()
        list.add(WeakReference(obj))
    }
    
    Runtime.getRuntime().gc()
    Runtime.getRuntime().runFinalization()
    Runtime.getRuntime().gc()
    
    list.forEach {
        println(it.get().toString())
    }
}
