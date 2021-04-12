// Original bug: KT-15049

fun main(args: Array<String>) {
    request(mapOf("k1" to arrayOf("v11", "v12"), "k2" to "v2"))
}

fun request(params: Map<String, Any> = emptyMap()) = {}
