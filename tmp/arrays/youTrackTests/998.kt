// Original bug: KT-45066

import java.nio.file.*
import kotlin.reflect.*

fun main() {
    println(returnTypeOf { Paths.get("/foo") })
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> returnTypeOf(block: () -> T) =
    typeOf<T>()
