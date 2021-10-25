// Original bug: KT-10919

fun <T, R> T.apply(block: T.() -> R) = block() // result can be coerced to Unit
fun <T, R> T.str(block: T.() -> R) = block().toString() // result CAN NOT be coerced to Unit
