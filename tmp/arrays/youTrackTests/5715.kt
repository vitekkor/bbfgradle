// Original bug: KT-25000

class Person {
    var name: String? = null
}

fun main(args: Array<String>) {
    val name = "John Doe"
    val person = Person().apply {
        this.name = name
    }
    println(person.name) // "John Doe"
}
