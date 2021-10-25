// Original bug: KT-27846

inline fun <reified T : Any> type() = T::class

fun main(args: Array<String>) {
    val f1: () -> Int = { 1 }
    val f2: () -> String = { "a" }
    println(f1::class) //class ch.tutteli.atrium.TestKt$main$f1$1
    println(type<() -> Int>()) //class kotlin.Function0

    println(f1::class.isInstance(f1)) // true
    println(f1::class.isInstance(f2)) // false

    println(type<() -> Int>().isInstance(f1)) // true
    println(type<() -> Int>().isInstance(f2)) // true -> should be false
}
