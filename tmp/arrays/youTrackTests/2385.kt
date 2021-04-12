// Original bug: KT-18199

import java.util.function.Consumer

val function: Consumer<out String?> = TODO()

fun take(f: Consumer<out String>) {}

fun main(args: Array<String>) {
    take(function)
}
