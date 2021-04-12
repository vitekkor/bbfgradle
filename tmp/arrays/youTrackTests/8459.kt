// Original bug: KT-20625

fun main(args: Array<String>) {
    val a = object : A {}
    val b = object : B {}
    println(a.greet())
    println(a.greet("world"))
    println(b.greet())
    println(b.greet("world"))
}

interface A {
    fun greet(name: String = "everybody"): String = "Hello, $name!"
}

interface B : A {
    override fun greet(name: String): String = "Hi, $name!"
}
