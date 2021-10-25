// Original bug: KT-44726

import java.nio.file.*
import kotlin.reflect.*

fun main() {
    println(returnTypeOf { Paths.get("/foo") })
    // IR:  java.nio.file.Path? (actual)
    // Old: java.nio.file.Path  (expected)
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T: Any> returnTypeOf(block: () -> T) =
    typeOf<T>()
