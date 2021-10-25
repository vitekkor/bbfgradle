// Original bug: KT-20625

fun main(args: Array<String>) {
    val a = object : A {}
    val c = object : C {}
    println(a.greet())
    println(a.greet("world"))
    println(c.greet())
    println(c.greet("world"))
}

interface A {
    fun greet(name: String = "everybody"): String = "Hello, $name!"
}

interface B : A {
    override fun greet(name: String): String
}

interface C : B {
    override fun greet(name: String): String = "Hi, $name!"
}
