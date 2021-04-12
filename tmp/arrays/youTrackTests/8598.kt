// Original bug: KT-13932

fun test1(): Unit = Unit
fun test2(): Unit? = Unit

val testList = listOf(
        test1(),    // undefined
        test2(),    // kotlin.Unit
        Unit        // kotlin.Unit
)
