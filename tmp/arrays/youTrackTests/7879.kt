// Original bug: KT-24818

class Ref<K : Comparable<K>, V>(val key: K, val value: V) : Comparable<Ref<K, *>> {
    override fun compareTo(other: Ref<K, *>) = key.compareTo(other.key)
}

fun <K : Comparable<K>, V> smooth(
    refs: List<Ref<K, V>>,
    offset: K,
    normalizer: (value: K, before: K, after: K) -> Double,
    interpolator: (origin: V, target: V, ratio: Double) -> V
): V {
    val index = refs.binarySearchBy(offset) { it.key }
    if (index >= 0) return refs[index].value
    val before = refs.getOrNull(-index - 2)
    val after = refs.getOrNull(-index - 1)
    return when {
        before != null && after != null -> interpolator(
            before.value,
            after.value,
            normalizer(offset, before.key, after.key)
        )
        before != null && after == null -> before.value
        before == null && after != null -> after.value
        else -> error("No refs found at $offset")
    }
}

interface Info<T> {
    fun interpolate(origin: T, target: T, ratio: Double): T
}

fun Double.lerp(target: Double, ratio: Double) = this + (target - this) * ratio
fun Double.unlerp(min: Double, max: Double) = if (min == max) 0.0 else minus(min) / (max - min)
fun Int.unlerp(min: Int, max: Int) = toDouble().unlerp(min.toDouble(), max.toDouble())

object DoubleInfo : Info<Double> {
    override fun interpolate(origin: Double, target: Double, ratio: Double) = origin.lerp(target, ratio)
}

class Prop<T>(val info: Info<T>) {
    val refs = mutableListOf<Ref<Int, T>>()
}

class Container {
    val smooths = mutableListOf<Prop<*>>()
}

fun main(args: Array<String>) {
    val container = Container()
    val prop = Prop(DoubleInfo)
    container.smooths += prop
    prop.refs += Ref(0, 4.0)
    prop.refs += Ref(10, 9.0)

    val atThree = container.smooths.map {
        smooth(
            it.refs as List<Ref<Int, Any>>,
            3,
            Int::unlerp,
            it.info::interpolate as (Any, Any, Double) -> Any
        )
    }
    println(atThree)
}
