// Original bug: KT-2373

fun Pair<out Iterable<Int>, out Iterable<String>>.each(f : (Int, String) -> Unit) {}
fun <K,V> zip(key:Iterable<K>, value: Iterable<V>, f : (K, V) -> Unit) {}
