// Original bug: KT-13069

interface A<K, V> : MutableMap<K, V>

class B : A<String, String>, java.util.AbstractMap<String, String>() {
    override val entries: MutableSet<MutableMap.MutableEntry<String, String>>
        get() = java.util.HashSet()
}

fun box(): String {
    val x1 = B().remove("1", "2")
    if (x1) return "fail 1"

    return "OK"
}

fun main(args: Array<String>) {
    print(box())
}
