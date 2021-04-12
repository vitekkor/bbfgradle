// Original bug: KT-8133

fun main(args: Array<String>) {
    println("Hello")
    val baz = 1.let {
       object : foo {
           override fun bar() = println(it)
       }
    }
    baz.bar()
}

interface foo {
    fun bar()
}
