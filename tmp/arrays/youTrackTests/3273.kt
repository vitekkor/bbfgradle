// Original bug: KT-36745

class Thing(val jvmType: java.lang.reflect.Type)

fun filterNumbers(list: List<Thing>): List<Thing> =
    list.filter {
        val clazz = it.jvmType as? Class<*> ?: return@filter false
        Number::class.java.isAssignableFrom(clazz)
    }
