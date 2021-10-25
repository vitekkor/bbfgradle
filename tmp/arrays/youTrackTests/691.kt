// Original bug: KT-43751

data class Person(val name: String, val age: Int)

fun main() {
    println(mapOf(1 to "one", 2 to "two").toString())
    if (mapOf(1 to "one", 2 to "two").toString() == """mapOf(1 to "one", 2 to "two")""") {
        println("map")
    }
    println(listOf("one", "two", "three").toString())
    if (listOf("one", "two", "three").toString() == """listOf("one", "two", "three")""") {
        println("list")
    }
    println(Person(name="Joe \"Awesome\" Smith", age=32).toString())
    if (Person(name="Joe \"Awesome\" Smith", age=32).toString() == """Person(name="Joe \"Awesome\" Smith", age=32)""") {
        println("data class")
    }
}
