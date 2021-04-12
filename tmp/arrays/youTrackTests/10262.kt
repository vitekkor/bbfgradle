// Original bug: KT-5414

fun foo(t: Class<*>) {
    if (t.isArray()) {
        val componentType = t.getComponentType()!!
        val array = java.lang.reflect.Array.newInstance(componentType, 10)
    }
}
