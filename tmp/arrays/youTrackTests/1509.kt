// Original bug: KT-34826

import java.util.function.Function

fun box(): String {
    val map = mutableMapOf<Function<Any, Any>, String>()
    val fn = Function<Any, Any> { TODO() }
    return map.computeIfAbsent(fn, { "OK" })
}
