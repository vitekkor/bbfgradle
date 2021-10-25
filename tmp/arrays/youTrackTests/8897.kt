// Original bug: KT-18045

class User(val map: Map<String, Any?>) {
    val name: String by map
}

fun main(args: Array<String>) {
    val user = User(mapOf("name" to 42))
    println(user.name + user.name) // actually this is number
}
