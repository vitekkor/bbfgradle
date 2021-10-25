// Original bug: KT-11851

fun <K, V> ((K) -> V?).withDefault(default: (K) -> V): (K) -> V = { k: K -> this(k) ?: default(k) }

fun main(args: Array<String>) {
    val map = mapOf(1 to "a")
    val mapGet = map::get.withDefault { "default" }
  
    println(mapGet(1))
    println(mapGet(2))
}
