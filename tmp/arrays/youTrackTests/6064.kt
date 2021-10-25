// Original bug: KT-12518

interface Fizz {
    fun example(): String
}

open class Buzz : Fizz {
    override fun example() = System.lineSeparator()
}

class Wat : Buzz() {
    override fun example(): String? = null
}

fun main(args: Array<String>) {
    println((Wat() as Fizz).example())
}
