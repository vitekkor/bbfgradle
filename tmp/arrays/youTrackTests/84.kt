// Original bug: KT-45888

// A value :)
abstract class Value

// A value might be a string
class StringValue(private val value: String) : Value() {
    override fun toString() = value
}

// Or it could be an object mapping string keys -> Values
class ObjectValue(private val contents: Map<String, Value>) : Value() {
    fun containsKey(k: String) = contents.containsKey(k)
    fun getValue(k: String): Value? = contents[k]
}

// Find out if there is any cool_stuff under a subkey!
fun detectCoolStuff(obj: ObjectValue): String {
    return obj.run {
        if (containsKey("subkey")) {
            getValue("subkey").run {
                if (containsKey("cool_stuff")) { // Break on this line and evaluate `containsKey("cool_stuff")`
                    "Cool stuff detected! It was ${getValue("cool_stuff")}"
                } else {
                    "No cool stuff detected"
                }
            }
        } else {
            "Nothing nested, can't have been cool"
        }
    }
}

fun main() {
    // An ObjectValue with some nesting
    val obj = ObjectValue(mapOf(
        "subkey" to ObjectValue(mapOf(
            "cool_stuff" to StringValue("beans")
        ))
    ))

    println(detectCoolStuff(obj))
}
