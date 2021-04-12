// Original bug: KT-17384


fun returnMaybeNull():String? {
    return if (System.currentTimeMillis() == 0L) null else "thing"
}

internal inline fun Array<String>.matchAll(collector: (String) -> Unit) {
    for (string in this) {
        // Works:
        // val returned = returnMaybeNull() ?: continue
        // collector(returned)

        // Crashes:
        collector(returnMaybeNull() ?: continue)
    }
}

fun main(args: Array<String>) {
    arrayOf("strings").matchAll {
    }
}
