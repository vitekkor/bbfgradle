// Original bug: KT-26117

package test

fun nothing_to_do_with_anything() = listOf<String>().associate { it to it }
fun nothing_to_do_with_anything_either() = listOf<String>().mapNotNull { null }

open class Parent

class Child : Parent()

class StupidThings(map: Map<String, List<Parent>>) {

    private val _map = HashMap<String, List<Child>>()

    init {
        map.forEach { (key, bindings) ->
            _map[key] = bindings.map { it as? Child ?: Child() }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun foo(): List<Pair<String, List<Child>>> {
        return _map.keys.map { realKey -> realKey to _map[realKey]!! }
    }

}

fun main(args: Array<String>) {
    val test = StupidThings(mapOf("coucou" to listOf(Parent())))
    test.foo()
}
