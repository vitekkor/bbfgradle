// Original bug: KT-17879

fun main(args: Array<String>) {
    val s = ""
    val i = 0
    val b = false
    
    println(s::class == String::class)
    println(i::class == Int::class)
    println(b::class == Boolean::class)

    fun test(a: Any) = when (a::class) {
        String::class -> println("String")
        Int::class -> println("Int")
        Boolean::class -> println("Boolean")
        else -> println("Else")
    }

    test(s)
    test(i)
    test(b)
}
