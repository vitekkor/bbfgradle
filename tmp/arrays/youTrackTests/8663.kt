// Original bug: KT-17464

abstract class AbstractIdentifier(val map: LinkedHashMap<String, Any?>) :
    Map<String, Any?> by map

class MyIdentifier(primary: String, secondary: String) :
    AbstractIdentifier(linkedMapOf("primary" to primary,
                                   "secondary" to secondary))

fun main(args: Array<String>) {
	MyIdentifier("first", "second")
}
