// Original bug: KT-17879

fun main(args: Array<String>) {
    foo(1)
}

inline fun <reified T : Any> foo(arg: T) {
    val intClass = Int::class
    println(T::class == Int::class.java)
    println(T::class == intClass) 
//     when (null) { Int::class.java, intClass -> { println("hello") } }
    println(T::class == intClass)
}
