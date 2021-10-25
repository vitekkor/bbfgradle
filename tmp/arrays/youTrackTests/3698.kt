// Original bug: KT-35105

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1

class Testclass {
    var entry: String = ""

    fun <T : Any> distinct(distinctField: KMutableProperty1<out Any, T>, entryClazz: KClass<T>, vararg filter: String) {
        return
    }

    inline fun <reified T : Any> distinct(distinctField: KMutableProperty1<out Any, T>, vararg filter: String) {
        return distinct(distinctField, T::class, *filter)
    }

    fun test() {
        distinct(Testclass::entry, "filter")
    }
}
