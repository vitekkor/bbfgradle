// Original bug: KT-44466

interface Setter<K, V> {
    operator fun set(k: K, v: V)
}
interface Getter<K, V> {
    operator fun get(k: K): V
}

class ValWrapper <K, V>(val getter: (K) -> V) : Getter<K, V> {
    override operator fun get(k: K) = getter(k)
}

class VarWrapper<K, V>(
    val getter: (K) -> V,
    val setter: (K, V) -> Any?,
) : Getter<K, V>, Setter<K, V> {
    override operator fun set(k: K, v: V) { setter(k, v) }
    override operator fun get(k: K) = getter(k)
}

class SetWrapper<K, V>(val setter: (K, V) -> Any?) : Setter<K, V> {
    override operator fun set(k: K, v: V) { setter(k, v) }
}

class Example {
    fun setA(k: String, v: String) = println("$k = $v")
    fun getA(k: String) = "getting $k".also(::println)

    val a = VarWrapper(::getA, ::setA)
    val b = ValWrapper(::getA)
    val c = SetWrapper(::setA)
}

fun main() {
    val e = Example()
    e.setA("k", "v") // lame
    println(e.a["k"])
    println(e.b["k"])
    e.c["k"] = "1" // epic
}
