// Original bug: KT-17879

fun main(args: Array<String>) {
    foo(1)
}

inline fun <reified T : Any> foo(arg: T) {
    println(T::class == Int::class.java)
    println(T::class == Int::class)
    when (T::class) { Int::class.java, Int::class -> { println("hello") } }
}
