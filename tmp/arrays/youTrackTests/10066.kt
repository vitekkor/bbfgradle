// Original bug: KT-9637

package reified

import java.util.*
import kotlin.reflect.KClass

val valuesInjectFnc = HashMap<KClass<out Any>, Any>()

inline fun <reified T : Any> registerFnc(noinline value: Function0<T>) {
    valuesInjectFnc[T::class] = value
}

inline fun <reified T : Any> injectFnc(): Lazy<Function0<T>> = lazy(LazyThreadSafetyMode.NONE) {
    (valuesInjectFnc[T::class] ?: throw Exception("no inject ${T::class.simpleName}")) as Function0<T>
}

class Box

class Boxer{
    val box:()->Box by injectFnc()
}

fun main(args: Array<String>) {
    val box = Box()
    registerFnc({box})
    println(Boxer().box())
}
