// Original bug: KT-44826

fun <T> foo(comparator: kotlin.Comparator<in T>, a: T, b: T) = comparator.compare(a, b)

fun bar(x: Int, y: Int) = foo<Int> ({ a, b -> a - b}, x, y)

fun main() = println(bar(42, 117))
