// Original bug: KT-17811

//Some.kt
interface A<T>
inline fun <reified T> foo() {
    object : A<T> {}
}

//Main.kt
fun main(args: Array<String>) {
    println("hello") // Evaluate foo<String>()
}
