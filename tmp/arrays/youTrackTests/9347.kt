// Original bug: KT-14194

import java.util.*

class MyMap : Map<String, String> {
    override val entries: Set<Map.Entry<String, String>>
        get() = setOf(object : Map.Entry<String, String> {
            override val key: String
                get() = "key"

            override val value: String
                get() = "value"
        })

    override val keys: Set<String>
        get() = setOf("key")

    override val size: Int
        get() = 1

    override val values = listOf("value")

    override fun containsKey(key: String) = key == "key"

    override fun containsValue(value: String) = value == "value"

    override fun get(key: String) = if (key == "key") "value" else null

    override fun isEmpty() = false
}

fun main(args: Array<String>) {
    HashMap<String, String>().putAll(MyMap())
}
