// Original bug: KT-33212

import java.util.*

class Foo : HashMap<String, String>() {
    operator fun set(x: String, y: String) {
        println("wrong method")
    }
}

fun main() {
    Foo().put("x", "y") // false positive "map.put() should be converted to assignment"
}
