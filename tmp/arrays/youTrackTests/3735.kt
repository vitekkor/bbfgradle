// Original bug: KT-20849

fun <T> something(): T = Any() as T

class Context<T>

fun <T> Any.decodeIn(typeFrom: Context<in T>): T = something()

fun <T> Any?.decodeOut(typeFrom: Context<out T>): T {
	return this?.decodeIn(typeFrom) ?: error("")  // decodeIn result is of type Nothing
}

fun main(args: Array<String>) {
	val r = "str".decodeOut(Context<Any>())
	println(r)
}
