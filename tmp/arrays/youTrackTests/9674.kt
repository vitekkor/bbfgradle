// Original bug: KT-3529

fun <T> foo(vararg values: T): Array<out T> {
    return bar(*values, *values)
}

fun <T> bar(vararg values: T) = values

fun main(args: Array<String>) {
    println(foo(1,2,3).joinToString { it.toString() })
}
