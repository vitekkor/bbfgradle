// Original bug: KT-17748

fun main(args: Array<String>) {
    println(42.doSwitchInt()) // blam!
    println("".doSwitchString()) // success
}

inline fun <reified E> E.doSwitchInt(): String = when(E::class) {
    Int::class -> "success!"
    else -> "blam!"
}

inline fun <reified E> E.doSwitchString(): String = when(E::class) {
    String::class -> "success!"
    else -> "blam!"
}
