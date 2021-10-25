// Original bug: KT-29347

import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass

fun foo(k: KClass<*>) {
    try {
    } catch (e: InvocationTargetException) {
    }
}
