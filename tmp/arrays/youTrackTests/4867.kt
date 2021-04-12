// Original bug: KT-19001

inline fun <reified T : Any> Map<*, *>.getAs(key: Any?) = this[key] as? T

fun foo(map: Map<String, String>, keys: Map<Any, Any>) {
    map[keys.getAs<String>("")] // explicit type is flagged as redundant here, removing it leads to red code
}
