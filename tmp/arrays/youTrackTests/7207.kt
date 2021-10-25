// Original bug: KT-28433

@Target(AnnotationTarget.TYPE)
annotation class Ann(val t: String)

typealias Test<@Ann("") T> = List<T> // OK

fun main() {
    val a: Test<Int> = listOf(1)
    println(a)
}
