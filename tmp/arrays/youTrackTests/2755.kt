// Original bug: KT-40746

class A<out T>
inline fun <reified T> A<T>.toTypedArray(): Array<T> = TODO()

fun test(a: A<String>) {
    create(a.toTypedArray()) // OI: Array<String>, NI: Array<Any>
}
fun create(any: Any) {
}
fun create(array: Array<Any>) {
}
