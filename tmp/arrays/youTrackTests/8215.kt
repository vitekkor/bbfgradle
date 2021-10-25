// Original bug: KT-22410

import java.util.Objects

fun main(args: Array<String>) {
    defineFunc<String>()

    func(1)
}

var func: (Any) -> Unit = {}

inline fun <reified T> defineFunc() {
    func = {
        val nullable = it as? T

        if (nullable == null)
            println("== catched it")

        if (Objects.equals(nullable, null))
            println("objects equals catched it")
    }
}
