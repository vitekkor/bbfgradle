// Original bug: KT-41927

fun main(args: Array<String>) {
    println(f<Int>())
    println(f<Int?>()) // should this be allowed?
}
inline fun <reified T> f(): Any = T::class // or rather should this be allowed?
