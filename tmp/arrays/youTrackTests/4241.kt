// Original bug: KT-37425

import kotlin.reflect.*

fun launch(c: () -> Unit) = c()

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> foo() = launch {
    println(typeOf<List<T>>()) // using reified T inside non-inline lambda
}

fun main() {
    foo<Int>()
}
