// Original bug: KT-23834

class Shadow {
    fun shade() {}
}

fun <X> Shadow.shade() {}

fun context() {
    Shadow().shade<String>() // Try code completion inside `shade`: the extension is not suggested.
}

