// Original bug: KT-39962

fun doThing(): String {
    return doInternalThing("thing")
}

internal fun doInternalThing(parameter: String): String {
    return parameter
}

fun main() {
    println(doThing())
}
