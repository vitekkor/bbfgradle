// Original bug: KT-7265

class MethodCache(val klass: Class<*>) {
    val foo = klass.getDeclaredMethod("foo").let { method ->
        fun(instance: Any?) = method(instance) // error: Function declaration must have a name :(
    }
}
