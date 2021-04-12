// Original bug: KT-26704

package test

val <T : Number> List<T>.valTest1: (T) -> T
    get() = fun(x: T) = x

fun test1() = listOf(1, 2, 3).valTest1(42) // Error on '42', see below
