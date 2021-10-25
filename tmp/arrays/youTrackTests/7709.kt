// Original bug: KT-26124

import kotlin.reflect.KProperty1
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty

class Value<T>(var value: T = null as T, var text: String? = null)

val <T> Value<T>.additionalText by DVal(Value<T>::text) //works

class DVal<T, R, P: KProperty1<T, R>>(val kmember: P) {
    operator fun getValue(t: T, p: KProperty<*>): R {
        return kmember.get(t)
    }
}

fun main(args: Array<String>) {
    val p = Value("O", "K")
    println(p.additionalText)
}
