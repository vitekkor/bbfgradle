// Original bug: KT-16864

object Whatever {
    operator fun getValue(thisRef: Any?, prop: Any?) = "Whatever"
}

fun main(args: Array<String>) {
    val key by Whatever
    run {
        object {
            val keys = key
        }
    }
}
