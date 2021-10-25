// Original bug: KT-10662

fun f(a: Any) {
    if (a is Map.Entry<*, *>) {
        val y: Any? = a!!.key // unresolved key
    }
}
fun f2(a: Any?) {
    if (a is Map.Entry<*, *>?) {
        val y: Any? = a!!.key  // unresolved key
    }
}
