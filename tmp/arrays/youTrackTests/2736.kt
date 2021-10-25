// Original bug: KT-40654

import kotlin.reflect.KFunction1

fun a(type: Array<String>): Array<String> = arrayOf("a")

fun b(type: Array<Int>): Array<Int> = arrayOf(1)

fun c(type: Boolean): Boolean = false

val map: Map<String, KFunction1<*,*>> = mapOf(
        "a" to ::a,
        "b" to ::b
)

val map2: Map<String, KFunction1<*,*>> = mapOf<String, KFunction1<*,*>>(
        "a" to ::a,
        "b" to ::b
)

val map3: Map<String, KFunction1<*,*>> = mapOf(
        "a" to ::a,
        "c" to ::c,
        "b" to ::b
)
