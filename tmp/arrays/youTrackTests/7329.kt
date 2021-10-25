// Original bug: KT-27146

class Foo : java.util.LinkedHashMap<String, String>() {
    fun with(key: String,
             value: String): Foo {
        super.put(key, value)
        return this
    }
}
