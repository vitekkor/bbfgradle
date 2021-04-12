// Original bug: KT-7985

import java.util.*

fun <T> foo(x : List<T> = ArrayList<T>()) {} // OK

fun main(args: Array<String>)
{
    fun <T> bar(x : List<T> = ArrayList<T>()) {} // Kotlin: Unresolved reference: T
}
