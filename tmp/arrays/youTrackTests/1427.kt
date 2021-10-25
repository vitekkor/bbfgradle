// Original bug: KT-42697

// IGNORE_BACKEND_FIR: JVM_IR
// FULL_JDK
// WITH_RUNTIME
// JVM_TARGET: 1.8
// FILE: 1.kt
// !JVM_DEFAULT_MODE: enable

abstract class Base<K, V> : MutableMap<K, V> {

}

// FILE: main.kt
// !JVM_DEFAULT_MODE: all-compatibility
var result = ""
interface A<K, V> : MutableMap<K, V> {

    override fun remove(key: K): V? {
        result += key.toString() + ";"
        return null
    }

    override public fun getOrDefault(key: K, defaultValue: V): V {
        result += key.toString() + ";"
        return defaultValue
    }
}


internal class MyMap : Base<String, String>(), A<String, String> {
    override val size: Int
        get() = null!!

    override fun isEmpty(): Boolean {
        return true
    }

    override fun containsKey(key: String): Boolean {
        return false
    }

    override fun containsValue(value: String): Boolean {
        return false
    }

    override fun get(key: String): String? {
        TODO("Not yet implemented")
    }

    override fun put(key: String, value: String): String? {
        result += "$key=$value;"
        return null
    }

    override fun putAll(from: Map<out String, String>) {
    }

    override fun clear() {}

    override val keys: MutableSet<String>
        get() = null!!

    override val values: MutableCollection<String>
        get() = null!!

    override val entries: MutableSet<MutableMap.MutableEntry<String, String>>
        get() = null!!
}

fun box(): String {
    val map = MyMap()
    map["O"] = "fail"
    map.remove("O")

    val value = map.getOrDefault("O", "OK")
    if (result != "O=fail;O;O;") return "fail 3: $result"
    return value
}
