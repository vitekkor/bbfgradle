// Original bug: KT-5834

fun <T,K> Iterable<T>.distinct(keySelecter:(T)->(K)) :List<T>{
    val set = HashSet<K>()
    val list = ArrayList<T>()
    for (t in this) {
        val key = keySelecter(t)
        if (key !in set) {
            list.add(t)
            set.add(key)
        }
    }
    return list
}
