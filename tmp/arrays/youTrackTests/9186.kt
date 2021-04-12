// Original bug: KT-15891

import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

val String.ext: String by lazy { "" }

fun main(args: Array<String>) {
    val p = String::ext as KProperty1<Any, String> // or get via reflection API
    p.isAccessible = true
    println(p.getDelegate(42))
}
