// Original bug: KT-21524

import java.util.concurrent.Executors

fun main(args: Array<String>) {
    val executor = Executors.newSingleThreadExecutor()
    val execute: (() -> Unit) -> Unit = executor::execute
    val function = { }
    execute(function)
}
