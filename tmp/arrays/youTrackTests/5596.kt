// Original bug: KT-33312

// Main.kt
fun main() {
    println("breakpoint")
}
inline fun sameFileFun() = object : Function<String> {}

//AnotherOne.kt
inline fun foo() = object : Function<String> {}

fun notInline() = object : Function<String> {}
val valObj = object : Function<String> {}
inline fun notAnonObject() = Obj
object Obj : Function<String>
