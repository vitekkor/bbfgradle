// Original bug: KT-38962

import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
fun bar(f: () -> Int): String {
    return "${f()}"
}

@JvmName("whoCares")
fun bar(f: () -> String): String {
    return f()
}

fun main() {
    println(
        bar {
            3
        }
    )
}
