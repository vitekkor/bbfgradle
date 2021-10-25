// Original bug: KT-22884

class SimpleThing<T>
fun <X> inferInside(simpleThing: SimpleThing<X>) {}
class Place {
    val classLevel = inferInside<Short>(SimpleThing()) // Class level.
    fun level() {
        val funLevel = inferInside<Long>(SimpleThing()) // Inside function.
    }
} 