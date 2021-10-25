// Original bug: KT-25000

package foo

class Person {
    var name: String? = null
}

val name = "John Doe"
val person = Person().apply {
    this.name = foo.name
}

fun main(args: Array<String>) {
    println(person.name) // "John Doe"
}
