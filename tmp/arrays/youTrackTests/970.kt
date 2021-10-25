// Original bug: KT-40815

import kotlin.reflect.KProperty

inline operator fun String.getValue(thiz: Any?, property: KProperty<*>): String = property.name

fun box(): String = {
    val OK by ""
    OK
}()
