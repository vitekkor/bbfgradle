// Original bug: KT-39701

import kotlin.reflect.jvm.isAccessible

val thing by lazy { "" }

fun main(){
    ::thing.apply { isAccessible = true }::getDelegate.call()
    println("Here")
}
