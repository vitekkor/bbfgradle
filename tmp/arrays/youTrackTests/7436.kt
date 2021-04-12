// Original bug: KT-26891

class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}
fun main(args: Array<String>) {
    val user = User(mapOf(
            "name" to "John Doe",
            "age"  to null
    ))
    println("name = ${user.name}, age = ${user.age}")
}
