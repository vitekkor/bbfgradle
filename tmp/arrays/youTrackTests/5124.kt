// Original bug: KT-31231

import kotlin.reflect.full.isSubclassOf

fun main() {
    val x: Any = arrayOf("")

    // Should return true
    x::class.isSubclassOf(Array<Any>::class)

    // Should return true
    Array<String>::class.isSubclassOf(Array<Any>::class)
}
