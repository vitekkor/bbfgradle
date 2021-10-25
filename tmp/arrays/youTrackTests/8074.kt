// Original bug: KT-17637

class Foo {

    fun useMap() {
        if (propertyMap is Map<*, *>) {
            println(propertyMap.size)
        }
    }

    companion object {
        val propertyMap: Any = mapOf("x" to "value")
    }
}
